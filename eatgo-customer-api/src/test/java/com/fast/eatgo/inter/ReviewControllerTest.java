package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReviewService;
import com.fast.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidAttribute() throws Exception {
        given(reviewService.addReview(eq(1L), eq("JOKER"), eq(3), eq("Good"))).willReturn(
                Review.builder()
                        .id(1L)
                        .name("JOKER")
                        .score(3)
                        .description("Good")
                        .restaurantId(1L)
                        .build()
        );

        mvc.perform(post("/restaurant/1/review")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJUZXN0MSJ9.YKYvqkmiv5hWdGkREOqiELSWCzMQs7ABeQXbMTkyLMw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"score\":3,\"description\":\"Good\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurant/1/review/1"));

        verify(reviewService).addReview(any(), any(), any(), any());
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        mvc.perform(post("/restaurant/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"score\":,\"description\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

}