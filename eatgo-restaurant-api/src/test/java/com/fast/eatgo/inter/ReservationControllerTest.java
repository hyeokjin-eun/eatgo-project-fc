package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReservationService;
import com.fast.eatgo.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void list() throws Exception {
        Long restaurantId = 1L;

        List<Reservation> reservations = new ArrayList<>();

        reservations.add(Reservation.builder()
                .id(1L)
                .restaurantId(1L)
                .name("Test1")
                .date("2020-02-05")
                .time("12:00")
                .partySize(3)
                .build());

        given(reservationService.getReservations(restaurantId)).willReturn(reservations);

        mvc.perform(get("/reservation")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJUZXN0MSJ9.YKYvqkmiv5hWdGkREOqiELSWCzMQs7ABeQXbMTkyLMw"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":")));

        verify(reservationService).getReservations(restaurantId);
    }

}