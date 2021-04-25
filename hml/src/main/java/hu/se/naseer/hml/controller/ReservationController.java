package hu.se.naseer.hml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.se.naseer.hml.model.Reservation;
import hu.se.naseer.hml.model.User;
import hu.se.naseer.hml.service.ReservationService;

@RestController
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation,
    @AuthenticationPrincipal User user
    ){
        Reservation saveReservation = reservationService.addReservation(reservation);
        return new ResponseEntity<>(saveReservation, HttpStatus.CREATED);
    }

    @GetMapping("/reservatopm/{uid}")
    public ResponseEntity<List<Reservation>> getOrders(@PathVariable("uid") Integer user_id){
        List<Reservation> saveOrders = reservationService.getReservationsByUserId(user_id);
        return new ResponseEntity<>(saveOrders , HttpStatus.OK);
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getAllOrders(){
        List<Reservation> Orders = reservationService.getReservations();
        return new ResponseEntity<>(Orders , HttpStatus.OK);
    }

    @DeleteMapping("/reservation/delete/{rid}")
        public ResponseEntity<String> deleteReservation(@PathVariable("rid") Integer reservation_id){
            reservationService.deleteReservation(reservation_id);
            return new ResponseEntity<>("Reservation deleted!",HttpStatus.OK);
        }

}
