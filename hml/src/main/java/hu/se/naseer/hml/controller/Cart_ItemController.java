package hu.se.naseer.hml.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.se.naseer.hml.dto.Cart_itemDto;
import hu.se.naseer.hml.model.Cart;
import hu.se.naseer.hml.model.CartItem;
import hu.se.naseer.hml.model.User;
import hu.se.naseer.hml.repository.UserRepository;
import hu.se.naseer.hml.service.CartService;

@RestController
public class Cart_ItemController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cart/check")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User checkAccess(){
        
        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username ="";
        // if(auth.getPrincipal() instanceof UserDetails){
        //      username = ((UserDetails) auth.getPrincipal()).getUsername();
        // }else{
        //      username = auth.getPrincipal().toString();
        // }
        return user;
    }

    @PostMapping("/cart/add/{iid}/{qty}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Set<Cart_itemDto>> addToCart(@PathVariable("iid") Integer item_id,
    @PathVariable("qty") Integer quantity
    ){
        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();
        
        cartService.addItemToCart(user, item_id, quantity);
        Set<CartItem> cartItems = user.getCart().getItems();
        Set<Cart_itemDto> cartItemsDto = cartService.convertToDto(cartItems);
        return new ResponseEntity<>(cartItemsDto , HttpStatus.CREATED);
    }

    @GetMapping("/cart")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Set<Cart_itemDto>> Cart_items(){

        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        Cart userCart  = user.getCart();
        Set<CartItem> items = userCart.getItems();
        Set<Cart_itemDto> cartItemsDto = cartService.convertToDto(items);
        return new ResponseEntity<>(cartItemsDto, HttpStatus.CREATED);
    }

    @PutMapping("/cart/update/{iid}/{qty}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Set<Cart_itemDto>> updateItemQuantity(@PathVariable("iid") Integer item_id,
    @PathVariable("qty") Integer quantity
    ){

        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        cartService.updateItem(user, item_id, quantity);
        Set<CartItem> cartItems = user.getCart().getItems();
        Set<Cart_itemDto> cartItemsDto = cartService.convertToDto(cartItems);
        return new ResponseEntity<>(cartItemsDto , HttpStatus.OK);
    }

    @DeleteMapping("/cart/{iid}/delete")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteCartItem(@PathVariable("iid") Integer item_id
    ){
        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        cartService.removeItem(user, item_id);
        return new ResponseEntity<>("Item "+item_id + " deleted from cart for user "+user.getUsername() +"!", HttpStatus.OK);
    }
}
