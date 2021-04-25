package hu.se.naseer.hml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.se.naseer.hml.dto.RoomDto;
import hu.se.naseer.hml.model.Room;
import hu.se.naseer.hml.service.RoomService;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;
   

    @PostMapping("/room/add")
    public ResponseEntity<RoomDto> addroom(@RequestBody Room room){
        Room saveroom =  roomService.addRoom(room);
        RoomDto saveroomDto = roomService.convertToDto(saveroom);
        return new ResponseEntity<>(saveroomDto , HttpStatus.CREATED);
    }

    @GetMapping("/room")
    public ResponseEntity<List<RoomDto>> getrooms(){
        List<Room> rooms = roomService.getRooms();
        List<RoomDto> roomsDtoList = roomService.convertToDto(rooms);
        return new ResponseEntity<>(roomsDtoList, HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<RoomDto> getroom(@PathVariable("id") Integer id){
        Room room = roomService.getRoomById(id);
        RoomDto roomDto = roomService.convertToDto(room);
        return new ResponseEntity<>(roomDto , HttpStatus.OK);
    }

    @PutMapping("/room/update")
    public ResponseEntity<RoomDto> updateroom(@RequestBody Room room){
        Room updateroom = roomService.addRoom(room);
        RoomDto updatedroomDto = roomService.convertToDto(updateroom);
        return new ResponseEntity<>(updatedroomDto , HttpStatus.OK);
    }

    @DeleteMapping("/room/{id}/delete")
    public ResponseEntity<String> deleteroom(@PathVariable Integer id){
        roomService.deleteRoomById(id);
        return new ResponseEntity<>("room "+id+" delete",HttpStatus.OK);
    }
}
