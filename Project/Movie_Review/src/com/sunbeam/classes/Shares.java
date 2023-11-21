package com.sunbeam.classes;

public class Shares {
	int review_id;
	int user_id;
	
	public Shares(int review_id, int user_id) {
		this.review_id = review_id;
		this.user_id = user_id;
	}
	public Shares() {
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("shares [review_id=").append(review_id).append(", user_id=").append(user_id).append("]");
		return builder.toString();
	}
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
