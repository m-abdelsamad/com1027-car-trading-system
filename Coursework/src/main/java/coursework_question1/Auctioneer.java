package coursework_question1;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Auctioneer {
	
	
	/**
	 * the following fields represent the name of the Auctioneer, and 3 maps (carsForSale, soldCars, unsoldCars)
	 */
	protected String name;
	protected Map<Advert, User> carsForSale;
	protected Map<Advert, User> soldCars;
	protected Map<Advert, User> unsoldCars;
	
	
	
	/**
	 * This constructor initializes the name of the auctioneer and initializes carsForSale, soldCars and unsoldCars as hash maps 
	 * @param name
	 */
	public Auctioneer(String name) {
		super();
		this.name = name;
		this.carsForSale = new HashMap<Advert, User>();
		this.soldCars = new HashMap<Advert, User>();
		this.unsoldCars = new HashMap<Advert, User>();
	}

	/**
	 * the following method is a simple getter for the 'name' of the auctioneer
	 */
	public String getName() {
		return this.name;
	}
	
	
	
	/**
	 * This method loops through the carsForSale hash map and checks if a certain car already exits.
	 * if the car does exist, the method will return true otherwise false.
	 * @param car
	 * @return
	 */
	private boolean checkExistence(Car car) {
		for(Map.Entry<Advert, User> entry: this.carsForSale.entrySet()) {
			if(entry.getKey().getCars() == car) {
				return true;
			}
		}
		return false;
		
	}
	
	
	/**
	 * This method will take the 'car', which is placed inside the 'carAdvert' object, and register it if the 
	 * car doesn't exist in the 'carsForSale' hash map.
	 * It will then take the car and set the relevant color, type, body and noOfSeats and added it to the 'carsForSale'
	 * hash map.
	 * 
	 * if the carAdvert object is null, the method will throw a IllegalArgumentException
	 * 
	 * @throws IllegalArgumentException
	 */
	public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int numberOfSeats) throws IllegalArgumentException {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		if(!this.checkExistence(carAdvert.getCars())) {
			
			Car car = carAdvert.getCars();
			car.setBody(body);
			car.setColour(colour);
			car.setGearbox(type);
			car.setNumberOfSeats(numberOfSeats);

		
			if(!this.carsForSale.containsKey(carAdvert)) {
				this.carsForSale.put(carAdvert, user);
			}
		}
		
		
	}
	
	
	/**
	 *This method will check if the car is on the for sale hash map, if it is then you can place an offer the method will return 'true'
	 *if the car is not on the for sale hash map then you are not able to place the offer and 'false' will be returned.
	 *
	 *if the carAdvert object is null, the method will throw a IllegalArgumentException
	 *@throwsIllegalArgumentException
	 */
	public boolean placeOffer(Advert carAdvert, User user, double value) throws IllegalArgumentException{
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		if(this.carsForSale.containsKey(carAdvert)) {
			carAdvert.placeOffer(user, value);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Once a sale ends, the car sold will be added to the 'soldCars' hash map and will be removed from the 'carsForSale' hash map 
	 * indicating that the car is no longer for sale. However for the sale to be made the offer made for the car must be higher than the
	 * car price and higher than any other offer made (this is why the 'getHighestOffer' method is used)
	 * If the car is not sold, the car will be added to the unsoldCars hash map.
	 * 
	 * @param advert
	 * @throws IllegalArgumentException
	 */
	public void endSale(Advert advert) throws IllegalArgumentException {
		if(advert == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		User user = advert.getHighestOffer().getBuyer();
		if(advert.getHighestOffer().getValue() >= advert.getCars().getPrice()) {
			this.soldCars.put(advert, user);
			this.carsForSale.remove(advert);
		} else {
			this.unsoldCars.put(advert, user);
			this.carsForSale.remove(advert);
		}
	}
	
	
	/**
	 * This method will display all the unsold cars from the unsoldCars hash map. this is done by looping through the 
	 * hash map and extracting the relevant data (it will call the toString method from Advert class)
	 */
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		
		for(Map.Entry<Advert, User> entry: unsoldCars.entrySet()) {
			result += entry.getKey().toString() + "\n" ;
		}
		
		return result;
	}
	
	
	/**
	 * This method will display the sold cars alongside the relevant data about the person who purchased a specific car.
	 * This is done by looping through the hash map
	 */
	public String displaySoldCars() {
		DecimalFormat df = new DecimalFormat("0.00");
		String result = "SOLD CARS:\n";
		for(Map.Entry<Advert, User> entry: soldCars.entrySet()) {
			result += entry.getKey().getCars().getID() + " - Purchased by " + entry.getValue().getName() + " with a successfull £"
					+ df.format(entry.getKey().getHighestOffer().getValue()) + " bid. \n";
		}
		return result; 
	}
	
	
	/**
	 * this methods will simply just return 'statistics'
	 * @return
	 */
	public String displayStatistics() {
		return "Statistics";
	}
	
	
}
