package com.ecom.review.rating.request;

public class ReviewRequest {

	private long productId;
	private int ratingStars;
	private String review;

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
