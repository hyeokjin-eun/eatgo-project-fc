package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReservationService;
import com.fast.eatgo.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/restaurant/{restaurantId}/reservation")
    public ResponseEntity<?> created(@PathVariable Long restaurantId) throws URISyntaxException {

        Reservation reservation = reservationService.addReservation();

        return ResponseEntity
                .created(new URI("/restaurant/" + restaurantId + "/reservation"))
                .body(reservation);
    }
}
