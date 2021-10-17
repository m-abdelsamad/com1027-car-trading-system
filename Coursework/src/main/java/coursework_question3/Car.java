package coursework_question3;

import java.text.DecimalFormat;

public class Car {
	
	
	/**
	 * The following fields represent the properties of the car
	 */
	private int id;
	private String name;
	private String colour;
	private double reservedPrice;
	private CarType gearbox;
	private CarBody body;
	private int numberOfSeats;
	private Condition condition;
	private SaleType type;
	
	
	/**
	 * this constructor initializes each individual property of the car.
	 * if the id is negative a IllegalArgumentException is thrown
	 * if the name of the car is Null a IllegalArgumentException is thrown
	 * if the price of the car is negative a IllegalArgumentException is thrown
	 * @param id
	 * @param name
	 * @param reservedPrice
	 * @param condition
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public Car (int id, String name, double reservedPrice, Condition condition, SaleType type) throws IllegalArgumentException {
		if(id < 0) {
			throw new IllegalArgumentException ("ID cant be less than zero");
		} this.id = id;
		
		if(name == null) {
			throw new IllegalArgumentException ("Car name cant be null");
		} this.name = name;
		
		if(reservedPrice < 0) {
			throw new IllegalArgumentException ("Price cant be less than zero");
		} this.reservedPrice = reservedPrice;
		
		this.condition = condition;
		this.type = type;
	}

	
	/**
	 * The following methods are simple getters and setters for the properties of the car
	 * (id, name, price, gearbox, body, colour, number of seats, condition and type)
	 */
	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getColour() {
		return this.colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getPrice() {
		return this.reservedPrice;
	}

	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public CarType getGearbox() {
		return this.gearbox;
	}

	public void setGearbox(CarType gearbox) {
		this.gearbox = gearbox;
	}

	public CarBody getBodyStyle() {
		return this.body;
	}

	public void setBody(CarBody body) {
		this.body = body;
	}

	public Condition getCondition() {
		return this.condition;
	}
	
	public SaleType getType() {
		return this.type;
	}

	
	
	
	/**
	 * this toString method returns the ID of the car and its name in a certain format
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.name;
	}

	
	
	/**
	 * This method returns a string that contains all the details of the car,
	 * (gearbox, body, colour, number of seats, condition)
	 */
	public String displayCarSpecification() {
		DecimalFormat df = new DecimalFormat("0.00");
		return this.toString() + " (£" + df.format(reservedPrice) + ")\n" 
				+ "	 Type: " + this.gearbox + "\n"
				+ "	 Style: " + this.body + "\n"
				+ "	 Colour: " + this.colour + "\n"
				+ "	 No. of Seats: " + this.numberOfSeats + "\n"
				+ "	 Condition: " + this.condition;
	}
	
}
