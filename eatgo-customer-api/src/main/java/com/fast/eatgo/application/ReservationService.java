package com.fast.eatgo.application;

import com.fast.eatgo.domain.Reservation;
import com.fast.eatgo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long userId, String name, String date, String time, Integer partySize) {
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        Reservation newReservation = reservationRepository.save(reservation);

        return newReservation;
    }
}
