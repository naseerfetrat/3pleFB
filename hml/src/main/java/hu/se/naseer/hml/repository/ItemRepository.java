package hu.se.naseer.hml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.se.naseer.hml.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
}
