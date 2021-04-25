package hu.se.naseer.hml.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created on 4/14/2021.
 */
@Entity
@Builder
@Getter
@Setter
@ToString(exclude = {"user", "items"})
@EqualsAndHashCode(exclude = "items")
public class Cart {

    Cart() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToOne
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> items = new HashSet<>();

    public void addItem(CartItem cartItem) {
        if (items == null)
            items = new HashSet<>();

        items.add(cartItem);
    }


    public Optional<CartItem> findItem(Item item) {
        if (items == null)
            return Optional.empty();
        return getItems()
                .stream()
                .filter(cartItem ->
                        cartItem.getItem() != null
                                && Objects.equals(cartItem.getItem().getId(), item.getId()))
                .findFirst();
    }

    public void removeItem(CartItem removeItem) {
        if (items != null) {
            boolean result =items.removeIf(cartItem -> Objects.equals(cartItem.getId(), removeItem.getId()));
            if(result){
            System.out.println("item removed");
            }
        }
    }

    public void updateItem(CartItem cartItem) {
        removeItem(cartItem);
        addItem(cartItem);
    }

    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    public Cart(Long id, User user, Set<CartItem> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }
}
