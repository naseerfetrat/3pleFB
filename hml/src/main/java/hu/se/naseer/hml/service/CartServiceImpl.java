package hu.se.naseer.hml.service;



import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.se.naseer.hml.dto.Cart_itemDto;
import hu.se.naseer.hml.model.Cart;
import hu.se.naseer.hml.model.CartItem;
import hu.se.naseer.hml.model.Item;
import hu.se.naseer.hml.model.User;
import hu.se.naseer.hml.repository.CartRepository;
import hu.se.naseer.hml.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by naseerfetrat on 4/14/2021.
 */
@Service()
public class CartServiceImpl implements CartService {

    private final UserRepository userService;

    private final CartRepository cartRepository;

    private final ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(UserRepository userService, CartRepository cartRepository, ItemService itemService) {
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.itemService = itemService;
    }


    @Transactional
    @Override
    public Cart addItemToCart(User user, Integer itemId , Integer quantity) {
        if (user.getId() == null) {
            LOGGER.info("UserId is not set for the user? check to see if the user is valid");
            Optional<User> optionalUser = userService.findByUsername(user.getUsername());
            user = optionalUser
                    .orElseThrow(() -> new RuntimeException("there is no user with this username!"));
        }

        Item item  = itemService.getItemById(itemId);
        return updateCart(user, item, quantity);
    }

    @Override
    public Cart removeItem(User user, Integer itemId) {

        Item item = itemService.getItemById(itemId);
        
        return removeItemFromCart(user, item);
    }

    @Override
    public Cart removeItem(User user, Item item) {

        return removeItemFromCart(user, item);

    }

    @Override
    public Cart clear(User user) {
        final User cartUser = user;
        Optional<Cart> optionalCart = cartRepository.findByUser(user);
        return optionalCart
                .map(cart -> {
                    cart.getItems().clear();
                    return cart;
                }).orElseGet(() -> {
                    Cart cart = Cart.builder().user(cartUser).build();
                    return cartRepository.save(cart);
                });

    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Optional<Cart> findByUsername(String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        return optionalUser.flatMap(user -> Optional.of(user.getCart()));
    }

    @Override
    public Optional<Cart> findByUserId(Long id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.flatMap(user -> Optional.of(user.getCart()));
    }

    @Override
    public Cart updateItem(User user, Integer itemId, Integer itemCount) {
        Item item  = itemService.getItemById(itemId);
        return updateCart(user, item, itemCount);
    }

    private Cart removeItemFromCart(User user, Item item) {
        final User cartUser = user;
        Optional<Cart> optionalCart = cartRepository.findByUser(user);
        return optionalCart.map(cart -> {
            Optional<CartItem> cartitem = cart.findItem(item);
            cartitem.ifPresent(cart::removeItem);
            // Set<CartItem> cartitems = user.getCart().getItems();
            // for(CartItem newItem : cartitems){
            //     if(newItem.getItem() == item){
            //         cartitems.remove(newItem);
            //         cart.setItems(cartitems);
            //         System.out.println(newItem.getItem().getName());
            //     }
            // }
            return cartRepository.save(cart);
        }).orElseGet(() -> {
            Cart cart = Cart.builder().user(cartUser).build();
            return cartRepository.save(cart);
        });
    }

    private Cart updateCart(User user, Item item, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        final Cart mCart = optionalCart.map(cart -> {
            Optional<CartItem> optionalItem = cart.findItem(item);
            CartItem updatedCartItem = optionalItem.map(cartItem -> {
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(quantity * item.getPrice());
                return cartItem;
            }) 
            .orElse(
                CartItem.builder()
                    .cart(cart)
                    .item(item)
                    .quantity(quantity)
                    .unitPrice(quantity * item.getPrice())
                    .build());

            cart.updateItem(updatedCartItem);
            cart.setUser(user);
            user.setCart(cart);
            return cart;
            

        }).orElse(Cart.builder().user(user).build());
        return cartRepository.save(mCart);
    }


    @Override
    public Set<Cart_itemDto> convertToDto(Set<CartItem> items) {
        Set<Cart_itemDto> itemDtoSet = new HashSet<>();
        for(CartItem item : items){
            Cart_itemDto cart_itemDto = modelMapper.map(item, Cart_itemDto.class);
            itemDtoSet.add(cart_itemDto);
        }
        return itemDtoSet;
    }


    @Override
    public Cart_itemDto convertToDto(CartItem item) {
        Cart_itemDto cart_itemDto = modelMapper.map(item, Cart_itemDto.class);
            return cart_itemDto;
    }
    
}
