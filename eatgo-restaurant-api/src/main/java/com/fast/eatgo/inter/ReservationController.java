package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReservationService;
import com.fast.eatgo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public List<Reservation> list(Authentication authentication) {
        Claims claims = (Claims) authentication.getPrincipal();
        Long restaurantId = claims.get("userId", Long.class);
        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        return reservations;
    }
}
