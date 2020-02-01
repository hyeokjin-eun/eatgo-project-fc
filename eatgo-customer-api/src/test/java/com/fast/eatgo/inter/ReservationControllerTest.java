package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReservationService;
import com.fast.eatgo.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void created() throws Exception {
        Long restaurantId = 1L;
        Long userId = 1L;
        String name = "Test1";
        String date = "2020-01-25";
        String time = "12:00";
        Integer partySize = 5;

        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        given(reservationService.addReservation(restaurantId, userId, name, date, time, partySize)).willReturn(reservation);

        mvc.perform(post("/restaurant/1/reservation")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJUZXN0MSJ9.YKYvqkmiv5hWdGkREOqiELSWCzMQs7ABeQXbMTkyLMw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"name\":\"Test1\",\"date\":\"2020-01-25\",\"time\":\"12:00\",\"partySize\":5}"))
                .andExpect(status().isCreated());

        verify(reservationService).addReservation(restaurantId, userId, name, date, time, partySize);
    }
}