package com.ecom.review.rating.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.review.rating.client.ProductClient;
import com.ecom.review.rating.entity.ReviewEntity;
import com.ecom.review.rating.exception.InvalidProductException;
import com.ecom.review.rating.exception.NoProductFoundException;
import com.ecom.review.rating.exception.ReviewExceptionHandler;
import com.ecom.review.rating.repository.ReviewRepository;
import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ProductResponse;
import com.ecom.review.rating.response.ReviewResponse;

@Service
public class ReviewServiceImple implements ReviewService{

    private final ReviewExceptionHandler reviewExceptionHandler;

	@Autowired
	ProductClient productClient;
	
	@Autowired
	ReviewRepository reviewRepository;

    ReviewServiceImple(ReviewExceptionHandler reviewExceptionHandler) {
        this.reviewExceptionHandler = reviewExceptionHandler;
    }
	
	@Override
	public long writeReview(ReviewRequest reviewRequest) {
		long productId = reviewRequest.getProductId();
		ProductResponse response = productClient.fetchProduct(productId);
		if(response.getProductName() == null) {
			throw new NoProductFoundException("No product found for the product id: " + productId);
		}
		
		ReviewEntity entity = new ReviewEntity();
		entity.setProductId(reviewRequest.getProductId());
		entity.setRatingStars(reviewRequest.getRatingStars());
		entity.setReview(reviewRequest.getReview());
		entity = reviewRepository.save(entity);
		return entity.getReviewId();
	}

	@Override
	public List<ReviewResponse> fetchReview(long productId) {
		List<ReviewEntity> entities = reviewRepository.findByProductId(productId);
		
		if(entities==null || entities.isEmpty()) {
			throw new InvalidProductException("No record found. Invaid product id: " + productId);
		}
		
		List<ReviewResponse> al = new ArrayList<ReviewResponse>();
		for(ReviewEntity entity : entities) {
			ReviewResponse response = new ReviewResponse();
			response.setReviewId(entity.getReviewId());
			response.setReview(entity.getReview());
			response.setRatingStars(entity.getRatingStars());
			al.add(response);
			
		}
		return al;
	}

}
