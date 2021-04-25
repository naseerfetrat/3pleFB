// package hu.se.naseer.hml.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import hu.se.naseer.hml.model.Cart_item;
// import hu.se.naseer.hml.model.Item;
// import hu.se.naseer.hml.model.User;

// @Repository
// public interface Cart_itemRepository extends JpaRepository<Cart_item,Integer>{
    
//     public List<Cart_item> findByUser(User user);

//     public void deleteByUserAndItem(Long user_id , Integer item_id);

//     public void updateQuantity(Integer quantity , Integer user_id , Integer item_id);

//     public Cart_item findByUserandItem(User user, Item item);
// }
