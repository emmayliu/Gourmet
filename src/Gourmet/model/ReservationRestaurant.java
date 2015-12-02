package Gourmet.model;

import java.sql.Time;

public class ReservationRestaurant extends Restaurants {
	protected int maxRoomSize;
		
	public ReservationRestaurant (String restaurantId, String name, boolean acceptsCreditCard, boolean WIFI, 
			int priceRange, Time open,Time close, int noiseLevel, String neighborhood, double star, int parking, 
			String street, String city, String state, int zipCode, int maxRoomSize ) {
		super(restaurantId, name, acceptsCreditCard, WIFI, priceRange, open, close, noiseLevel, neighborhood,
				star, parking, street, city, state, zipCode);
		this.maxRoomSize = maxRoomSize;
	}
	
	public ReservationRestaurant(String restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public void setMaxRoomSize(int maxRoomSize) {
		this.maxRoomSize = maxRoomSize;
	}
	
	public int getMaxRoomSize() {
		return maxRoomSize;
	}

}