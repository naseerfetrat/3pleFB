package hu.se.naseer.hml.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
/**
 * Created on 4/14/2021.
 */
@Entity
@Builder
@Setter
@Getter
@ToString(exclude = {"item"})
@EqualsAndHashCode(exclude = {"cart"})
public class CartItem {

    

    private Long id;
    private Item item;
    private Cart cart;
    private int quantity;
    private Double unitPrice;

    CartItem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "item_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public CartItem(Long id, Item item, Cart cart, int quantity, Double unitPrice) {
        this.id = id;
        this.item = item;
        this.cart = cart;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


}
