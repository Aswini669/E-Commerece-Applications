package com.ecom.review.rating.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ReviewResponse;
import com.ecom.review.rating.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<String> addProductReview(@RequestBody ReviewRequest reviewRequest){
		logger.info("Add product review request received: {}", reviewRequest);
		long reviewId = reviewService.writeReview(reviewRequest);
		return ResponseEntity.ok("Review added successfully. Review id: " + reviewId);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ReviewResponse>> getReview(@PathVariable long productId){
		logger.info("Get product review request received: {}",productId);
		List<ReviewResponse> response = reviewService.fetchReview(productId);
		return ResponseEntity.ok(response);
	}
}
