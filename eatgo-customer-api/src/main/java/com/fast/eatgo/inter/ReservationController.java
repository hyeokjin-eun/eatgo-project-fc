package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReservationService;
import com.fast.eatgo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<?> created(Authentication authentication, @PathVariable Long restaurantId, @RequestBody Reservation resource) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);
        String date = resource.getDate();
        String time = resource.getTime();
        Integer partySize = resource.getPartySize();

        Reservation reservation = reservationService.addReservation(userId, name, date, time, partySize);

        return ResponseEntity
                .created(new URI("/restaurant/" + restaurantId + "/reservation"))
                .body(reservation);
    }
}
