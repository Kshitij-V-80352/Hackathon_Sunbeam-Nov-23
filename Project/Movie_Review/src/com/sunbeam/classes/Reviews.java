package com.sunbeam.classes;

import java.util.Date;

public class Reviews {
	int reviewId;
	int movieId;
	String review;
	int rating;
	int userId;
	java.util.Date modified;

	public Reviews(int reviewId, int movieId, String review, int rating, int userId, Date modified) {
		this.reviewId = reviewId;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.userId = userId;
		this.modified = modified;
	}

	public Reviews() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewId = ").append(reviewId).append(", movieId = ").append(movieId).append(", review = ")
				.append(review).append(", rating = ").append(rating).append(", userId = ").append(userId)
				.append(", modified = ").append(modified);
		return builder.toString();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public java.util.Date getModified() {
		return modified;
	}

	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}

}
