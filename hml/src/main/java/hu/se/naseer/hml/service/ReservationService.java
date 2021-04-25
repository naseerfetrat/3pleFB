package hu.se.naseer.hml.service;

import java.util.List;

import hu.se.naseer.hml.dto.ReservationDto;
import hu.se.naseer.hml.model.Reservation;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getReservations();
    List<Reservation> getReservationsByUserId(Integer id);
    Reservation getReservationById(Integer id);
    void deleteReservation(Integer id);


    List<ReservationDto> convertToDto(List<Reservation> reservations);
    ReservationDto convertToDto(Reservation reservation);
}
