package hu.se.naseer.hml.service;

import java.util.Optional;
import java.util.Set;
import hu.se.naseer.hml.dto.Cart_itemDto;
import hu.se.naseer.hml.model.Cart;
import hu.se.naseer.hml.model.CartItem;
import hu.se.naseer.hml.model.Item;
import hu.se.naseer.hml.model.User;

/**
 * Created using Intellij IDE
 * Created by naseerfetrat on 4/14/21.
 */
public interface CartService {


    Cart addItemToCart(User user, Integer itemId, Integer quantity);

    Cart removeItem(User user, Integer itemId);

    Cart removeItem(User user, Item item);

    Cart clear(User user);

    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUsername(String username);

    Optional<Cart> findByUserId(Long id);

    Cart updateItem(User user, Integer itemId, Integer itemCount);

    Set<Cart_itemDto> convertToDto(Set<CartItem> items);
    Cart_itemDto convertToDto(CartItem item);
}
