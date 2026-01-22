package com.ecom.review.rating.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewId;
	private long productId;
	private int ratingStars;
	private String review;

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getRatingStars() {
		return ratingStars;
	}

	public void setRatingStars(int ratingStars) {
		this.ratingStars = ratingStars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
