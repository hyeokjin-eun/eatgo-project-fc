package com.fast.eatgo.application;

import com.fast.eatgo.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    public Reservation addReservation(Long userId, String name, String date, String time, Integer partySize) {
        return Reservation.builder()
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();
    }
}
