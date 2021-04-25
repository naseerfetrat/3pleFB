package hu.se.naseer.hml.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Item
 */
@Entity
@Table(	name = "items")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Item {

    private Integer id;
    private String name;
    private String discription;
    private String image_url;
    private String category;
    private Double price;
    private Set<OrderItem> orderItems;
    private Set<CartItem> cartItems;

    

    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item() {
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Item(Integer id, String name, String discription, String image_url, String category,
            Set<OrderItem> orderItems, Set<CartItem> cartItems) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.image_url = image_url;
        this.category = category;
        this.orderItems = orderItems;
        this.cartItems = cartItems;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

   

   

   

    
    
}