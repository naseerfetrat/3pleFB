package hu.se.naseer.hml.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.se.naseer.hml.service.OrderService;
import hu.se.naseer.hml.dto.OrderDto;
import hu.se.naseer.hml.model.Order;
import hu.se.naseer.hml.model.User;
import hu.se.naseer.hml.repository.UserRepository;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/order/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<OrderDto> addOrder(@RequestBody Order order
    ){
        Object uss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) uss).getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.get();

        Order saveOrder = orderService.createOrder(user , order.getUser_address(), order.getPhone_number());
        OrderDto orderDto = orderService.convertToDto(saveOrder);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping("/order/{uid}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable("uid") Long user_id){
        List<Order> saveOrders = orderService.getOrdersByUserId(user_id);
        List<OrderDto> orderDtos = orderService.convertToDto(saveOrders);
        return new ResponseEntity<>(orderDtos , HttpStatus.OK);
    }

    @GetMapping("/order/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<Order> saveOrders = orderService.getOrders();
        List<OrderDto> orderDtos = orderService.convertToDto(saveOrders);
        return new ResponseEntity<>(orderDtos , HttpStatus.OK);
    }

}
