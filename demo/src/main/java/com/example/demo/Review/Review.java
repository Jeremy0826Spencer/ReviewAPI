package com.example.demo.Review;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "Review"
)
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long reviewId;
    private Long reviewUserId;
    private Long reviewLocationId;
    private String reviewText;
    @CreationTimestamp
    private LocalDate reviewDate;

    public Long getReviewLocationId() {
        return this.reviewLocationId;
    }

    public void setReviewLocationId(Long reviewLocationId) {
        this.reviewLocationId = reviewLocationId;
    }

    public Long getReviewId() {
        return this.reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public LocalDate getReviewDate() {
        return this.reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewUserId() {
        return this.reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewText() {
        return this.reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Review() {
    }

    public Review(Long reviewUserId, Long reviewLocationId, String reviewText) {
        this.reviewUserId = reviewUserId;
        this.reviewLocationId = reviewLocationId;
        this.reviewText = reviewText;
    }

    public Review(Long reviewId, Long reviewUserId, String reviewText, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reviewUserId = reviewUserId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public Review(Long reviewId, Long reviewUserId, Long reviewLocationId, String reviewText, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reviewUserId = reviewUserId;
        this.reviewLocationId = reviewLocationId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public Review(Long reviewUserId, String reviewText, LocalDate reviewDate) {
        this.reviewUserId = reviewUserId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public String toString() {
        return "Review{reviewId=" + this.reviewId + ", reviewUserId=" + this.reviewUserId + ", reviewLocationId=" + this.reviewLocationId + ", reviewText='" + this.reviewText + "', reviewDate=" + this.reviewDate + "}";
    }
}

