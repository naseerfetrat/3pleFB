package hu.se.naseer.hml.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.se.naseer.hml.dto.RoomDto;
import hu.se.naseer.hml.model.Room;
import hu.se.naseer.hml.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
    
    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.getOne(id);
    }

    @Override
    public void deleteRoomById(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDto> convertToDto(List<Room> rooms) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for(Room room : rooms){
            RoomDto roomDto = modelMapper.map(room, RoomDto.class);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    @Override
    public RoomDto convertToDto(Room room) {
            RoomDto roomDto = modelMapper.map(room, RoomDto.class);
            return roomDto;
    }
    
}
