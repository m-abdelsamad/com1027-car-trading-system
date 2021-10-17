package coursework_question4;

public class Offer {
	
	/**
	 * This field represents the value that the buyer offers for a certain car
	 */
	private double value;
	
	/**
	 * This field represents User a complex object 
	 */
	private User buyer;
	
	
	/**
	 * This constructor initializes a buyer and the value
	 * if the buyer is null a IllegalArgumentException is thrown
	 * if the value is negative a IllegalArgumentException is thrown
	 * @param buyer
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public Offer(User buyer, double value) throws IllegalArgumentException {
		super();
		
		if(buyer == null) {
			throw new IllegalArgumentException ("buyer should not be null");
		} this.buyer = buyer;
		
		if(value <= 0) {
			throw new IllegalArgumentException ("enter a valid value");
		} this.value = value;
	}
	
	
	/**
	 * The following 2 methods are simple getters for the fields defined in this class 
	 */
	public double getValue() {
		return this.value;
	}
	
	public Buyer getBuyer() {
		return (Buyer) this.buyer;
	}
	
	
	
	/**
	 * This toString method returns the hidden buyer name (by calling the toString method from the User class)
	 * and the value the buyer offered 
	 */
	@Override
	public String toString() {
		return this.buyer.toString() + " offered £" + this.value;
	}
	
	
	
}
