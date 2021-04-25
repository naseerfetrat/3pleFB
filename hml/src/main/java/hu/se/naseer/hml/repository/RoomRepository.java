package hu.se.naseer.hml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.se.naseer.hml.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{
    
}
