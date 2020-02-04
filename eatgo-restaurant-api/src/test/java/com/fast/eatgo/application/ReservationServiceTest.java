package com.fast.eatgo.application;

import com.fast.eatgo.domain.Reservation;
import com.fast.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    public void getReservations() {
        Long restaurantId = 1L;

        List<Reservation> mockReservations = new ArrayList<>();

        mockReservations.add(Reservation.builder()
                .id(1L)
                .restaurantId(1L)
                .name("Test1")
                .date("2020-02-05")
                .time("12:00")
                .partySize(3)
                .build());

        given(reservationRepository.findAllByRestaurantId(restaurantId)).willReturn(mockReservations);

        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        assertThat(reservations.get(0).getId(), is(1L));
        assertThat(reservations.get(0).getRestaurantId(), is(1L));

        verify(reservationRepository).findAllByRestaurantId(any(Long.class));
    }
}