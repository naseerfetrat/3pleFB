package hu.se.naseer.hml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.se.naseer.hml.model.Order;
import hu.se.naseer.hml.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
    public List<Order> findByUser(User user);
    
}
