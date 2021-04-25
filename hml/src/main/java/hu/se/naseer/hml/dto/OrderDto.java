package hu.se.naseer.hml.dto;

import java.sql.Date;
import java.util.List;

public class OrderDto {

    private Integer id;
    private Date date;
    private String email;
    private Double subTotal;
    private Integer phone_number;
    private String orderKey;
    private String user_address;
    private Integer userId;
    private List<Integer> itemsIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }


    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getItemsIds() {
        return itemsIds;
    }

    public void setItemsIds(List<Integer> itemsIds) {
        this.itemsIds = itemsIds;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

}
