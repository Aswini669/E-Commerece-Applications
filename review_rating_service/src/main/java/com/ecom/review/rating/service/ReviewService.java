package com.ecom.review.rating.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ReviewResponse;

@Service
public interface ReviewService {

	long writeReview(ReviewRequest reviewRequest);
	List<ReviewResponse> fetchReview(long productId);
}
