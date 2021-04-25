package hu.se.naseer.hml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.se.naseer.hml.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
    public List<Reservation> findByUserId(Integer id);
    public void deleteByUserIdAndRoomId(Integer room_id , Integer user_id);
    
}
