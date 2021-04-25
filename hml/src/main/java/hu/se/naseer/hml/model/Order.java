package hu.se.naseer.hml.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(	name = "orders")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"items", "subTotal"})
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private Double subTotal;
    private Integer phone_number;
    private Date date;
    private String user_address;
    private String email;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();
    @Column(name = "order_key", updatable = false, nullable = false, length = 32)
    private String orderKey;


    public Order() {
    }

    @Transient
    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    public Order(Integer id, Double subTotal, Integer phone_number, Date date, String user_address, String email,
            User user, Set<OrderItem> items, String orderKey) {
        this.id = id;
        this.subTotal = subTotal;
        this.phone_number = phone_number;
        this.date = date;
        this.user_address = user_address;
        this.email = email;
        this.user = user;
        this.items = items;
        this.orderKey = orderKey;
    }


    

   


}
