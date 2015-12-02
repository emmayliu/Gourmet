

package Gourmet.model;

import java.sql.Time;


/**

 */
public class Restaurants {
	protected String restaurantId;
	protected String name;
	protected boolean acceptsCreditCard;
	protected boolean WIFI;
	protected int priceRange;
	protected Time open;
	protected Time close;
	protected int noiseLevel;
	protected String neighborhood;
	protected double star;
	protected int parking;
	protected String street;
	protected String city;
	protected String state;
	protected int zipCode;
		
	public Restaurants(String restaurantId, String name, boolean acceptsCreditCard, boolean WIFI, int priceRange, Time open,
			Time close, int noiseLevel, String neighborhood, double star, int parking, 
			String street, String city, String state, int zipCode) {
		this.restaurantId=restaurantId;
		this.name=name;
		this.acceptsCreditCard=acceptsCreditCard;
		this.WIFI=WIFI;
		this.priceRange=priceRange;
		this.open=open;
		this.close=close;
		this.noiseLevel = noiseLevel;
		this.neighborhood=neighborhood;
		this.star=star;
		this.parking=parking;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
	}
	
	public Restaurants(String restaurantId){
		this.restaurantId = restaurantId;
	}

	/** Getters and setters. */
	public void setRestaurantId(String restaurantId){
		this.restaurantId = restaurantId;
	}
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAcceptsCreditCard(Boolean creditcard){
		this.acceptsCreditCard = creditcard;
	}
	
	public Boolean getAcceptsCreditCard() {
		return acceptsCreditCard;
	}
	
	public void setWIFI(Boolean wifi){
		this.WIFI = wifi;
	}
	
	public Boolean getWIFI() {
		return WIFI;
	}
	
	public void setPriceRange(int priceRange){
		this.priceRange = priceRange;
		
	}
	
	public int getPriceRange(){
		return priceRange;
	}
    
	public void setOpen(Time open){
		this.open = open;
	}
		
	public Time getOpen(){
		return open;
	}
	
	public void setClose(Time close){
		this.close = close;
	}
	
	public Time getClose(){
		return close;
	}
	
	public void setNoiseLevel(int noiseLevel){
		this.noiseLevel = noiseLevel;
		
	}
	public int getNoiseLevel(){
		return noiseLevel;
	}
	
	public String getNeighborhood(){
		return neighborhood;
	}
	
	public void setStar(double star){
		this.star = star;
	}
	
	public double getStar(){
		return star;
	}
	
	public void setParking(int parking){
		this.parking = parking;
	}
	
	public int getParking(){
		return parking;
	}
	
	public String getStreet(){
		return street;
	}
	
	public String getCity(){
		return city;
	}
	public String getState(){
		return state;
	}
	public int getZipCode(){
		return zipCode;
	}
}

