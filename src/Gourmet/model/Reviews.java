

package Gourmet.model;

import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Users user;
	protected Restaurants restaurant;
	protected String review;
	protected Date created;
	protected float rating;
	
	public Reviews(int reviewId, Users user, Restaurants restaurant, String review, Date created, float rating) {
		this.reviewId = reviewId;
		this.user = user;
		this.restaurant = restaurant;
		this.review = review;
		this.created = created;
		this.rating = rating;
	}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(Users user, Restaurants restaurant, String review, Date created, float rating) {
		this.user = user;
		this.restaurant = restaurant;
		this.review = review;
		this.created = created;
		this.rating = rating;
	}
	
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Users getUser(){
		return user;
	}
	
	public void setUser(Users user){
		this.user = user;
	}
	
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	
	public Restaurants getRestaurant(){
		return restaurant;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getReview(){
		return review;
	}
    
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating){
		this.rating = rating;
	}
	

	
	
	
}
