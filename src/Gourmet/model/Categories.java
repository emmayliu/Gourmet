package Gourmet.model;

public class Categories {
	protected String restaurantId;
	protected boolean americanTraditional;
	protected boolean greek;
	protected boolean indian;
	protected boolean italian;
	protected boolean korean;
	protected boolean mexican;
	protected boolean thai;
	protected boolean bars;
	protected boolean buffets; 
	protected boolean seafood; 
	protected boolean steakHouse;
	protected boolean sandwiches;
	protected boolean pizza;
	
	public Categories(String restaurantId, boolean americanTraditional,boolean greek,boolean indian,boolean italian,
						boolean korean,boolean mexican,boolean thai,boolean bars,boolean buffets,boolean seafood,
						boolean steakHouse,boolean sandwiches,boolean pizza) {
		this.restaurantId = restaurantId;
		this.americanTraditional = americanTraditional;
		this.greek = greek;
		this.indian = indian;
		this.italian = italian;
		this.korean = korean;
		this.mexican = mexican;
		this.thai = thai;
		this.bars = bars;
		this.buffets = buffets;
		this.seafood = seafood;
		this.steakHouse = steakHouse;
		this.sandwiches = sandwiches;
		this.pizza = pizza;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public boolean getAmericanTraditional() {
		return americanTraditional;
	}

	public boolean getGreek() {
		return greek;
	}

	public boolean getIndian() {
		return indian;
	}

	public boolean getItalian() {
		return italian;
	}

	public boolean getKorean() {
		return korean;
	}

	public boolean getMexican() {
		return mexican;
	}

	public boolean getThai() {
		return thai;
	}

	public boolean getBars() {
		return bars;
	}

	public boolean getBuffets() {
		return buffets;
	}

	public boolean getSeafood() {
		return seafood;
	}

	public boolean getSteakHouse() {
		return steakHouse;
	}

	public boolean getSandwiches() {
		return sandwiches;
	}

	public boolean getPizza() {
		return pizza;
	}
}
