package coursework_question3;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * This class extends the functionality of the Dealership abstract class, and will override the 
 * abstract methods in Dealership class by writing the relevant code in the body of each method.
 *  
 * @author Mouaz Abdelsamad
 *
 */
public class Auctioneer extends Dealership {
	
	
	/**
	 * this Map contains the information about the seller and the number of sales they have
	 */
	private Map<Seller, Integer> sales;
	
	/**
	 * this field represents the seller that has the highest number of sales
	 */
	private Seller topSeller;
	
	
	/**
	 * this constructor initializes the name of the auctioneer using the 'super' as this class inherits or extends the functionality of 
	 * the dealership class
	 * 
	 * the constructor will also initialize the sales map as a hash map
	 * @param name
	 */
	public Auctioneer(String name) {
		super(name);
		this.sales = new HashMap<Seller, Integer>();
	}

	
	/**
	 * the following 2 methods are simple getters for the fields defined above
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	public Seller getTopSeller() {
		return this.topSeller;
	}
	
	
	
	
	
	/**
	 * This method loops through the carsForSale hash map and checks if a certain car already exits.
	 * if the car does exist, the method will return true otherwise false.
	 * @param car
	 * @return
	 */
	private boolean checkExistence(Car car) {
		for(Entry<Advert, Seller> entry: this.carsForSale.entrySet()) {
			if(entry.getKey().getCars() == car) {
				return true;
			}
		}
		return false;
		
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
		

		Seller seller = this.unsoldCars.get(advert);
		if(advert.getHighestOffer().getValue() >= advert.getCars().getPrice()) {
			this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
			this.carsForSale.remove(advert);
		} else {
			this.unsoldCars.put(advert, seller);
			this.carsForSale.remove(advert);
		}
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
	@Override
	public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int numberOfSeats) throws IllegalArgumentException {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException ("values cant be null");
		}
		
		Seller seller = this.carsForSale.get(carAdvert);
		if(!this.checkExistence(carAdvert.getCars())) {

			Car car = carAdvert.getCars();
			car.setBody(body);
			car.setColour(colour);
			car.setGearbox(type);
			car.setNumberOfSeats(numberOfSeats);
			if(!this.carsForSale.containsKey(carAdvert)) {
				this.carsForSale.put(carAdvert, seller);
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
	@Override
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
	 * This method will display all the unsold cars from the unsoldCars hash map. this is done by looping through the 
	 * hash map and extracting the relevant data (it will call the toString method from Advert class)
	 */
	@Override
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		for(Map.Entry<Advert, Seller> entry: unsoldCars.entrySet()) {
			result += entry.getKey().toString() + "\n" ;
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
		for(Map.Entry<Advert, Buyer> entry: soldCars.entrySet()) {
			result += entry.getKey().getCars().getID() + " - Purchased by " + entry.getValue() + " with a successful £"
					+ df.format(entry.getKey().getHighestOffer().getValue()) + " bid. \n";
		}
		return result; 
	}
	
	/**
	 * this methods will simply just return 'statistics'
	 * @return
	 */
	@Override
	public String displayStatistics() {
		return "Statistics";
	}
	
	
}
