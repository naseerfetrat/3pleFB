package hu.se.naseer.hml.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.se.naseer.hml.dto.ReservationDto;
import hu.se.naseer.hml.model.Reservation;
import hu.se.naseer.hml.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Reservation addReservation(Reservation reservation) {
        Reservation saveReservation = reservationRepository.save(reservation);
        return saveReservation;
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByUserId(Integer id) {
        return reservationRepository.findByUserId(id);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }


    @Override
    public List<ReservationDto> convertToDto(List<Reservation> reservations) {
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservations){
            ReservationDto ReservationDto = modelMapper.map(reservation, ReservationDto.class);
            reservationDtoList.add(ReservationDto);
        }
        return reservationDtoList;
    }

    @Override
    public ReservationDto convertToDto(Reservation reservation) {
            ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
            return reservationDto;
    }
    
}
