package coursework_question4;

import java.util.HashMap;
import java.util.Map;
/**
 * This class is an abstract class that will define the basic structure for 5 methods that will then be extended by
 * Auctioneer and Trader classes where the body of the methods will be written.
 *   
 * @author Mouaz Abdelsamad
 *
 */
public abstract class Dealership {
	
	/**
	 * This field represents the name of dealership
	 */
	protected String name;
	
	/**
	 * The following 3 fields defined are maps that include details of sold cars, non sold cars, and the cars for sale.
	 * these Maps will then be used in the Auctioneer and Trader class
	 */
	protected Map<Advert, Seller> carsForSale;
	protected Map<Advert, Buyer> soldCars;
	protected Map<Advert, Seller> unsoldCars;
	
	
	/**
	 * This constructor initializes the name of the dealership and initializes carsForSale, soldCars and unsoldCars as hash maps 
	 * @param name
	 */
	public Dealership (String name) {
		super();
		this.name = name;
		this.carsForSale = new HashMap<Advert, Seller>();
		this.soldCars = new HashMap<Advert, Buyer>();
		this.unsoldCars = new HashMap<Advert, Seller>();
	}
	
	
	/**
	 *The following abstract methods define the structure of each method that will be used later in the auctioneer and the trader class
	 */
	public abstract void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int numberOfSeats);
	
	public abstract boolean placeOffer(Advert carAdvert, User user, double value);
	
	public abstract String displayUnsoldCars();
	
	public abstract String displaySoldCars();
	
	public abstract String displayStatistics();
	
	
	
	

}
