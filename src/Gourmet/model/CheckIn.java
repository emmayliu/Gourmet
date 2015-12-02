package Gourmet.model;

public class CheckIn {
	protected int checkInId;
	protected String restaurantId;
	protected int sunday;
	protected int monday;
	protected int tuesday;
	protected int wednesday;
	protected int thursday;
	protected int friday;
	protected int saturday;
	
	
	public CheckIn (int checkInId, String restaurantId, int sunday, int monday, int tuesday,
		int wednesday, int thursday, int friday, int saturday) {
		
		this.checkInId = checkInId;
		this.restaurantId = restaurantId;
		this.sunday = sunday;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
	}
	
	public CheckIn (int checkInId){
		this.checkInId = checkInId;
	}
	
	public CheckIn (String restaurantId, int sunday, int monday, int tuesday,
			int wednesday, int thursday, int friday, int saturday) {
			
			this.restaurantId = restaurantId;
			this.sunday = sunday;
			this.monday = monday;
			this.tuesday = tuesday;
			this.wednesday = wednesday;
			this.thursday = thursday;
			this.friday = friday;
			this.saturday = saturday;
		}
	
	public int getCheckInId() {
		return checkInId;
	}
	
	public void setCheckInId(int checkInId){
		this.checkInId = checkInId;
	}
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}
	
	public int getSunday(){
		return sunday;
	}
	
	public int getMonday(){
		return monday;
	}
	
	public int getTuesday(){
		return tuesday;
	}
	
	public int getWednesday(){
		return wednesday;
	}
	public int getThursday(){
		return thursday;
	}
	public int getFriday(){
		return friday;
	}
	
	public int getSaturday(){
		return saturday;
	}
}

