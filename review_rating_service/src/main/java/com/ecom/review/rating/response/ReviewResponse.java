package com.ecom.review.rating.response;

public class ReviewResponse {

	private long reviewId;
	private int ratingStars;
	private String review;

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
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
