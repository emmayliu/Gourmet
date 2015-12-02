package Gourmet.model;

public class GoodFor {
	protected String restaurantId;
	protected boolean brunch;
	protected boolean dinner;
	protected boolean breakfast;
	protected boolean lunch;
	protected boolean dessert;
	protected boolean lateNight;
	protected boolean kid;
	protected boolean groups;
	
	public GoodFor(String restaurantId,boolean brunch, boolean dinner, boolean breakfast, boolean lunch,
						boolean dessert, boolean lateNight, boolean kid, boolean groups) {
		this.restaurantId = restaurantId;
		this.brunch = brunch;
		this.dinner = dinner;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dessert = dessert;
		this.lateNight = lateNight;
		this.kid = kid;
		this.groups = groups;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public boolean getBrunch() {
		return brunch;
	}

	public boolean getDinner() {
		return dinner;
	}
	
	public boolean getBreakfast() {
		return breakfast;
	}
	
	public boolean getLunch() {
		return lunch;
	}
	
	public boolean getDessert() {
		return dessert;
	}
	
	public boolean getLateNight() {
		return lateNight;
	}
	
	public boolean getKid() {
		return kid;
	}

	public boolean getGroups() {
		return groups;
	}
}
