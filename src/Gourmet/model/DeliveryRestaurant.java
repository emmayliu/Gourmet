package Gourmet.model;

import java.sql.Time;

public class DeliveryRestaurant extends Restaurants {
	protected int maxDeliverTime;
		
	public DeliveryRestaurant (String restaurantId, String name, boolean acceptsCreditCard, boolean WIFI, 
			int priceRange, Time open,Time close, int noiseLevel, String neighborhood, double star, int parking, 
			String street, String city, String state, int zipCode, int maxDeliverTime) {
		super(restaurantId, name, acceptsCreditCard, WIFI, priceRange, open, close, noiseLevel, neighborhood,
				star, parking, street, city, state, zipCode);
		this.maxDeliverTime = maxDeliverTime;
	}
	
	public DeliveryRestaurant(String restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public void setMaxDeliverTime(int maxDeliverTime) {
		this.maxDeliverTime = maxDeliverTime;
	}
	
	public int getMaxDeliverTime() {
		return maxDeliverTime;
	}

}


