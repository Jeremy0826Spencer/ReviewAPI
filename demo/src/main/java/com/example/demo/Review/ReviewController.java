package com.example.demo.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        path = {"/api/v1/review"}
)
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = this.reviewService.getReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        try {
            Review createdReview = this.reviewService.createReview(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (InvalidReviewDataException var3) {
            return ResponseEntity.badRequest().body("Invalid review data: " + var3.getMessage());
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> itemOptional = this.reviewService.getReviewById(id);
        return itemOptional.isPresent() ? ResponseEntity.ok((Review)itemOptional.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping({"/{reviewId}"})
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview) {
        try {
            Review updatedReviewEntity = this.reviewService.updateReview(reviewId, updatedReview);
            return ResponseEntity.ok(updatedReviewEntity);
        } catch (ReviewNotFoundException var4) {
            return ResponseEntity.notFound().build();
        }
    }
}

