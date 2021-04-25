package hu.se.naseer.hml.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.se.naseer.hml.dto.OrderDto;
import hu.se.naseer.hml.model.Cart;
import hu.se.naseer.hml.model.CartItem;
import hu.se.naseer.hml.model.Order;
import hu.se.naseer.hml.model.OrderItem;
import hu.se.naseer.hml.model.User;
import hu.se.naseer.hml.repository.OrderRepository;
import hu.se.naseer.hml.repository.UserRepository;
import hu.se.naseer.hml.util.RandomStringGenerator;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public Order createOrder(User user ,String user_address , Integer phone_number) {
        if (user.getId() == null) {
            Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
            user = optionalUser
                    .orElseThrow(() -> new RuntimeException("There is no user with this username"));
        }

        Cart cart = user.getCart();
        if (cart.isEmpty())
            throw new RuntimeException("Cannot Create an order with an empty cart");

        Order order = Order.builder().user(user).build();

        final Set<CartItem> items = cart.getItems();
        Double subTotal = 0.0;
        // for(CartItem item : items){
        //     System.out.println(item.getItem().getName() + item.getItem().getPrice() +"-" +item.getUnitPrice());
        //     subTotal = subTotal + item.getUnitPrice();
        // }
        final Set<OrderItem> orderItems = items
                .stream()
                .map(cartItem -> OrderItem.builder()
                        .order(order)
                        .item(cartItem.getItem())
                        .quantity(cartItem.getQuantity())
                        .unitPrice(cartItem.getUnitPrice())
                        .build())
                .collect(Collectors.toSet());
        for(OrderItem orderItem : orderItems){
            System.out.println(orderItem.getItem().getName());
        }       
        order.setItems(orderItems);
        order.setPhone_number(phone_number);
        order.setUser_address(user_address);
        order.setEmail(user.getEmail());
        order.setSubTotal(subTotal);
        LocalDate curDate = LocalDate.now();
        int year = curDate.getYear();
        int month = curDate.getMonth().getValue();
        int day = curDate.getDayOfMonth();
        order.setDate( new Date(year , month , day));
        //assign a unique order confirmation key
        order.setOrderKey(RandomStringGenerator.nextSessionId());
        return orderRepository.save(order);
    }

    @Override
    public Order save(Order order) {
        Order saveOrder = orderRepository.save(order);
        return saveOrder;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        User user = userRepository.getOne(id);
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> convertToDto(List<Order> orders) {
        List<OrderDto> orderDtoSet = new ArrayList<>();
        for(Order order : orders){
            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setItemsIds(order.getItems().stream().map(item -> item.getId()).collect(Collectors.toList()));
            orderDtoSet.add(orderDto);
        }
        return orderDtoSet;
    }
    
    @Override
    public OrderDto convertToDto(Order order) {
            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            return orderDto;
    }
    
}
