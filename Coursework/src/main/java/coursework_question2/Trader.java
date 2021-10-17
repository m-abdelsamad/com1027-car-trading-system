package coursework_question2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Trader {
	
	
	/**
	 * the following fields represent the name of the Trader, and 3 maps (carsForSale, soldCars, unsoldCars)
	 */
	protected String name;
	protected Map<Advert, User> carsForSale;
	protected Map<Advert, User> soldCars;
	protected Map<Advert, User> unsoldCars;
	
	
	/**
	 * This constructor initializes the name of the Trader and initializes carsForSale, soldCars and unsoldCars as hash maps 
	 * @param name
	 */
	public Trader (String name) {
		this.name = name;
		this.carsForSale = new HashMap<Advert, User>();
		this.soldCars = new HashMap<Advert, User>();
		this.unsoldCars = new HashMap<Advert, User>();
	}


	/**
	 * This simple method retrieves the name of the Trader.
	 * @return
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
	private boolean checkExistance(Car car) {
		for(Entry<Advert, User> entry: this.carsForSale.entrySet()) {
			if(entry.getKey().getCars() == car) {
				return true;
			}
		} 
		return false;
	}
	
	
	/**
	 * This method will take the 'car', which is placed inside the 'carAdvert' object, and register it if car's sale
	 * type is 'Auction' and if the car doesn't exist in the 'carsForSale' hash map.
	 * It will then take the car and set the relevant color, type, body and noOfSeats and added it to the 'carsForSale'
	 * hash map. 
	 * 
	 * if the carAdvert object is null, the method will throw a IllegalArgumentException
	 * 
	 * @throws IllegalArgumentException
	 */
	public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int noOfSeats) {

		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		SaleType saletype = SaleType.AUCTION;
		if(!carAdvert.getCars().getType().equals(saletype)) {
		if(!this.checkExistance(carAdvert.getCars())) {
			Car car = carAdvert.getCars();
			car.setBody(body);
			car.setColour(colour);
			car.setNumberOfSeats(noOfSeats);
			car.setGearbox(type);
				if(!this.carsForSale.containsKey(carAdvert)) {
					this.carsForSale.put(carAdvert, user);
					}
			}
		}
		
	}
	
	
	/**
	 *This method will check if a car is available for sale and if an offer is placed.
	 *When an offer is made on a car that is available for sale, the endSale method will be called to check if that offer 
	 *placed is successful (the value of the offer must be higher than the existing offers)
	 *
	 *if the carAdvert object is null, the method will throw a IllegalArgumentException
	 *
	 *@throwsIllegalArgumentException
	 */
	public boolean placeOffer (Advert carAdvert, User user, double value) throws IllegalArgumentException {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
	
		if(this.carsForSale.containsKey(carAdvert) && carAdvert.placeOffer(user, value)) {
			this.endSale(carAdvert);
			return true;
		} 
		return false;
	}
	
	
	/**
	 * For a sale to be made a successful offer must be made on the car, meaning that the offer must be higher than the car price
	 * and higher than any other offer made (this is why the 'getHighestOffer' method is used)
	 * Once a sale ends, the car sold will be added to the 'soldCars' hash map and will be removed from the 'carsForSale' hash map 
	 * and added to the 'unsoldCars' hash map, indicating that the car is no longer for sale.
	 * 
	 * if the Advert object is null, an IllegalArgumentException is thrown
	 * @param advert
	 * @throws IllegalArgumentException
	 */
	private void endSale(Advert advert) throws IllegalArgumentException {
		if(advert == null) {
			throw new IllegalArgumentException("Values cant be null");
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
		
		for(Map.Entry<Advert, User> entry: this.unsoldCars.entrySet()) {
			result += entry.getKey().toString() + "\n";
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
		for(Map.Entry<Advert, User> entry: this.soldCars.entrySet()) {
			result += entry.getKey().getCars().getID() + " - Purchased by " + entry.getValue().getName() + " with a successful £"
					+ df.format(entry.getKey().getHighestOffer().getValue()) + " bid. \n" + "";
		}
		return result;
	}
	
	/**
	 * this methods will simply just return 'statistics'
	 */
	public String displayStatistics() {
		return "Statistics";
	}
	
}
