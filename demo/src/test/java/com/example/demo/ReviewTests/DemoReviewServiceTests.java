package com.example.demo.ReviewTests;

import com.example.demo.Review.Review;
import com.example.demo.Review.ReviewNotFoundException;
import com.example.demo.Review.ReviewRepository;
import com.example.demo.Review.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
public class DemoReviewServiceTests {
    @Autowired
    private ReviewService reviewService;
    @MockBean
    private ReviewRepository reviewRepository;
    private Review newReview;

    public DemoReviewServiceTests() {
    }

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setUp() {
        this.newReview = new Review(1L, 2L, 5L, "It was good", LocalDate.of(2012, 8, 7));
        Mockito.when((Review)this.reviewRepository.save((Review)Mockito.any(Review.class))).thenReturn(this.newReview);
    }

    @Test
    public void getReviewsTest() {
        Mockito.when(this.reviewRepository.findAll()).thenReturn((List) Stream.of(new Review(1L, 2L, 5L, "It was good", LocalDate.of(2012, 8, 7)), new Review(2L, 2L, 4L, "It was bad", LocalDate.of(2013, 7, 23))).collect(Collectors.toList()));
        Assertions.assertEquals(2, this.reviewService.getReviews().size());
    }

    @Test
    public void createReviewTest() {
        Review createdReview = this.reviewService.createReview(this.newReview);
        ((ReviewRepository) Mockito.verify(this.reviewRepository)).save((Review)Mockito.any(Review.class));
        Assertions.assertEquals(this.newReview, createdReview);
    }

    @Test
    public void testUpdateReviewExistingReview() {
        Long reviewId = 1L;
        Review updatedReview = new Review();
        Mockito.when(this.reviewRepository.findById(reviewId)).thenReturn(Optional.of(this.newReview));
        Review updatedReviewEntity = this.reviewService.updateReview(reviewId, updatedReview);
        ((ReviewRepository)Mockito.verify(this.reviewRepository, Mockito.times(1))).findById(reviewId);
        ((ReviewRepository)Mockito.verify(this.reviewRepository, Mockito.times(1))).save((Review)Mockito.any(Review.class));
        Assertions.assertEquals(updatedReview.getReviewText(), updatedReviewEntity.getReviewText());
    }

    @Test
    public void testUpdateReviewNotFound() {
        Long reviewId = 1L;
        Review updatedReview = new Review();
        Mockito.when(this.reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        try {
            this.reviewService.updateReview(reviewId, updatedReview);
        } catch (ReviewNotFoundException var4) {
            Assertions.assertEquals("Review with ID " + reviewId + " not found", var4.getMessage());
        }

        ((ReviewRepository)Mockito.verify(this.reviewRepository, Mockito.times(1))).findById(reviewId);
        ((ReviewRepository)Mockito.verify(this.reviewRepository, Mockito.never())).save((Review)Mockito.any(Review.class));
    }
}

