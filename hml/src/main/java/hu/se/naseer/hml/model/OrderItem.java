package hu.se.naseer.hml.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * Created on 2/14/2017.
 */
@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"item", "unitPrice"})
public class OrderItem {

    @Tolerate
    public OrderItem() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;

    private Double unitPrice;

}
