package Gourmet.model;

import java.sql.Time;

public class TakeOutRestaurant extends Restaurants {
	protected int maxWaitTime;
		
	public TakeOutRestaurant (String restaurantId, String name, boolean acceptsCreditCard, boolean WIFI, 
			int priceRange, Time open,Time close, int noiseLevel, String neighborhood, double star, int parking, 
			String street, String city, String state, int zipCode, int maxWaitTime ) {
		super(restaurantId, name, acceptsCreditCard, WIFI, priceRange, open, close, noiseLevel, neighborhood,
				star, parking, street, city, state, zipCode);
		this.maxWaitTime = maxWaitTime;
	}
	
	public TakeOutRestaurant(String restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
	public int getMaxWaitTime() {
		return maxWaitTime;
	}

}