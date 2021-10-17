package coursework_question3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class extends the functionality of the Dealership abstract class, and will override the 
 * abstract methods in Dealership class by writing the relevant code in the body of each method.
 *  
 * @author Mouaz Abdelsamad
 *
 */
public class Trader extends Dealership {
	
	/**
	 * This List contains information about all the sellers
	 */
	private List<Seller> sellers;
	
	
	/**
	 * The constructor initializes the ArrayList as well as the name of the Trader.
	 * @param name
	 */
	public Trader (String name) {
		super(name);
		this.sellers = new ArrayList<Seller>();
		
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
		for(Entry<Advert, Seller> entry: this.carsForSale.entrySet()) {
			if(entry.getKey().getCars() == car) {
				return true;
			}
		} 
		return false;
	}
	
	
	/**
	 * once a successful sale has been made, the sold car will be added to the 'soldCars' hash map and removed from the 'carsForSale'
	 * hash map 
	 * @param advert
	 * @throws IllegalArgumentException
	 */
	private void endSale(Advert advert) throws IllegalArgumentException {
		if(advert == null) {
			throw new IllegalArgumentException("Values cant be null");
		}
		
		this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
		this.carsForSale.remove(advert);
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
	@Override
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
					this.carsForSale.put(carAdvert, (Seller) user);
					}
			}
		}
	}
	
	/**
	 * this method will check if a successful offer has been made, meaning that the car must exist to place an offer on that 
	 * car and the offer made must be higher than any other offer that exists, this is why the 'getHighestOffer' method is used
	 */
	@Override
	public boolean placeOffer (Advert carAdvert, User user, double value) throws IllegalArgumentException {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		if(this.carsForSale.containsKey(carAdvert) && carAdvert.placeOffer(user, value) && carAdvert.getHighestOffer().getValue() >= carAdvert.getCars().getPrice()) {
			this.endSale(carAdvert);
			return true;
		} 
		return false;
	}
	
	
	/**
	 * This method will display all the unsold cars from the unsoldCars hash map. this is done by looping through the 
	 * hash map and extracting the relevant data (it will call the toString method from Advert class)
	 */
	@Override
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		
		for(Map.Entry<Advert, Seller> entry: this.unsoldCars.entrySet()) {
			result += entry.getKey().toString() + "\n";
		}
		
		return result;
	}
	
	/**
	 * This method will display the sold cars alongside the relevant data about the person who purchased a specific car.
	 * This is done by looping through the hash map
	 */
	@Override
	public String displaySoldCars() {
		DecimalFormat df = new DecimalFormat("0.00");
		String result = "SOLD CARS:\n";
		for(Map.Entry<Advert, Buyer> entry: this.soldCars.entrySet()) {
			Buyer buyer = this.soldCars.get(entry.getKey());
			result += entry.getKey().getCars().getID() + " - Purchased by " + buyer + " with a successful £"
					+ df.format(entry.getKey().getHighestOffer().getValue()) + " bid. \n" + "";
		}
		return result;
	}
	
	
	/**
	 * this methods will simply just return 'statistics'
	 */
	@Override
	public String displayStatistics() {
		return "Statistics";
	}
	
}
