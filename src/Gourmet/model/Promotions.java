package Gourmet.model;

import java.util.Date;

public class Promotions {
	protected int promotionId;
	protected Restaurants restaurant;
	protected Date startTime;
	protected Date endTime;
	protected Double discount;
	
	
	public Promotions(int promotionId, Restaurants restaurant, Date startTime, Date endTime, Double discount) {
		this.promotionId = promotionId;
		this.restaurant = restaurant ;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
	}
	
	public Promotions(int promotionId) {
		this.promotionId = promotionId;
	}
	
	public Promotions(Restaurants restaurant, Date startTime, Date endTime, Double discount) {
		this.restaurant = restaurant ;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
	}

	public int getPromotionId(){
		return promotionId;
	}
	
	public void setPromotionId(int promotionId){
		this.promotionId = promotionId;
	}
	
	
	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}


