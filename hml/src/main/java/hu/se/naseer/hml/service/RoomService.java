package hu.se.naseer.hml.service;

import java.util.List;

import hu.se.naseer.hml.dto.RoomDto;
import hu.se.naseer.hml.model.Room;

public interface RoomService {

    Room addRoom(Room room);
    List<Room> getRooms();
    Room getRoomById(Integer id);
    void deleteRoomById(Integer id);

    List<RoomDto> convertToDto(List<Room> rooms);
    RoomDto convertToDto(Room room);
    
}
