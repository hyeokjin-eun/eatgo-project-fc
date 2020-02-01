package com.fast.eatgo.application;

import com.fast.eatgo.domain.Reservation;
import com.fast.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {
        Long restaurantId = 1L;
        Long userId = 1L;
        String name = "Test1";
        String date = "2020-02-01";
        String time = "12:00";
        Integer partySize = 3;

        given(reservationRepository.save(any())).will(invocation -> invocation.getArgument(0));

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);
        assertThat(reservation.getName(), is(name));

        verify(reservationRepository).save(any(Reservation.class));
    }
}