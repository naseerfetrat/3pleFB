package hu.se.naseer.hml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.se.naseer.hml.model.Cart;
import hu.se.naseer.hml.model.User;

import java.util.Optional;

/**
 * Created on 4/14/2021.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);
}
