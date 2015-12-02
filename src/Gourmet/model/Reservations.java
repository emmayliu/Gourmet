
package Gourmet.model;

import java.util.Date;

public class Reservations {
	protected int reservationId;
	protected Users user;
	protected Restaurants restaurant;
	protected Date start_time;
	protected Date end_time;
	protected int size;

	public Reservations(int reservationId, Users user, Restaurants restaurant, Date start_time, Date end_time, int size) {
		this.reservationId = reservationId;
		this.user = user;
		this.restaurant = restaurant;
		this.start_time = start_time;
		this.end_time = end_time;
		this.size = size;
	}
	
	public Reservations(int reservationId) {
		this.reservationId = reservationId;
	}

	public Reservations(Users user, Restaurants restaurant, Date start_time, Date end_time, int size) {
		this.user = user;
		this.restaurant = restaurant;
		this.start_time = start_time;
		this.end_time = end_time;
		this.size = size;
	}
	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Date getStart() {
		return start_time;
	}

	public Date getEnd() {
		return end_time;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	
	public Restaurants getRestaurant() {
		return restaurant;
	}
	
}
