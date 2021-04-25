package hu.se.naseer.hml.service;

import java.util.List;

import hu.se.naseer.hml.dto.OrderDto;
import hu.se.naseer.hml.model.Order;
import hu.se.naseer.hml.model.User;

public interface OrderService {

    Order createOrder(User user , String user_address, Integer phone_number);
    Order save(Order order);

    List<Order> getOrders();
    List<Order> getOrdersByUserId(Long id);
    Order getOrderById(Integer id);
    void deleteOrderById(Integer id);

    List<OrderDto> convertToDto(List<Order> orders);
    OrderDto convertToDto(Order order);

}
