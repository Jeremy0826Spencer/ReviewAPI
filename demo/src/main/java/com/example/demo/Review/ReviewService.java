package com.example.demo.Review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        return this.reviewRepository.findAll();
    }

    public Review createReview(Review review) {
        return (Review)this.reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long id) {
        return this.reviewRepository.findById(id);
    }

    public Review updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> optionalReview = this.reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review existingReview = (Review)optionalReview.get();
            existingReview.setReviewText(updatedReview.getReviewText());
            return (Review)this.reviewRepository.save(existingReview);
        } else {
            throw new ReviewNotFoundException("Review with ID " + reviewId + " not found");
        }
    }
}
