package com.example.demo.ReviewTests;
/*
import com.example.demo.Review.Review;
import com.example.demo.Review.ReviewController;
import com.example.demo.Review.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebMvcTest({ReviewController.class})
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
@AutoConfigureMockMvc
@WithMockUser(username="admin", roles={"ADMIN"})
public class DemoReviewControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReviewService reviewService;
    private Review newReview;
    private Review newReview2;

    public DemoReviewControllerTests() {
    }

    @BeforeEach
    public void setUp() {
        this.newReview = new Review(1L, 2L, 5L, "It was good", LocalDate.of(2012, 8, 7));
        this.newReview2 = new Review(2L, 2L, 4L, "It was bad", LocalDate.of(2013, 7, 23));
    }

    @Test
    public void givenReviewList_whenGetReviews_thenReturnsReviewList() throws Exception {
        Mockito.when(this.reviewService.getReviews()).thenReturn((List) Stream.of(this.newReview, this.newReview2).collect(Collectors.toList()));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/review", new Object[0]))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()", new Object[0])
                        .value(2)).andExpect(MockMvcResultMatchers.jsonPath("$[0].reviewId", new Object[0])
                        .value(1)).andExpect(MockMvcResultMatchers.jsonPath("$[0].reviewText", new Object[0])
                        .value("It was good")).andExpect(MockMvcResultMatchers.jsonPath("$[1].reviewId", new Object[0])
                        .value(2)).andExpect(MockMvcResultMatchers.jsonPath("$[1].reviewText", new Object[0])
                        .value("It was bad"));
    }
}

*/