package coursework_question4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	 * When a car is sold, the number of sales (for the seller) will increase by 1 which is then added to the sales list.
	 * 
	 * @param advert
	 * @throws IllegalArgumentException
	 */
	public void endSale(Advert advert) throws IllegalArgumentException {
		if(advert == null) {
			throw new IllegalArgumentException ("values cant be null");
		}

		
		if(advert.getHighestOffer().getValue() >= advert.getCars().getPrice()) {
			this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
			Seller seller = carsForSale.get(advert);
			seller.setSales(seller.getSales() + 1);
			this.sales.put(seller, seller.getSales());
			this.carsForSale.remove(advert);
		}	else {
			this.unsoldCars.put(advert, carsForSale.get(advert));
			this.carsForSale.remove(advert);
		}
	}
	
	
	
	/**
	 * This method will take the 'car', which is placed inside the 'carAdvert' object, and register it if the 
	 * car doesn't exist in the 'carsForSale' hash map.
	 * It will then take the car and set the relevant color, type, body and noOfSeats and added it to the 'carsForSale'
	 * hash map.
	 * The updateStatistics method will be called here to set the seller with the registered car
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
		
		if(!this.checkExistence(carAdvert.getCars())) {
			Car car = carAdvert.getCars();
			car.setBody(body);
			car.setColour(colour);
			car.setGearbox(type);
			car.setNumberOfSeats(numberOfSeats);
			if(!this.carsForSale.containsKey(carAdvert)) {
				this.carsForSale.put(carAdvert, (Seller) user);
				this.updateStatistics((Seller) user);
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
	 * This method will display the statistics of each sale (percentage of automatic and manual cars, total sale of cars 
	 * and name of auctioneer).
	 * I have done so by creating a switch case statement which will check if the gearbox type is manual it will increment the number of manual
	 * cars otherwise it will increment the automatic cars. The percentage of each gearbox type is found using the formula defined in the
	 * percentageAuto and percentageManual local variables
	 * 
	 * Then all the relevant data will be added together in a string and will be returned
	 * as an output of this method
	 * 
	 * this method will also call the saveInFile method which will write the statistics of the sold cars in a text file
	 */
	@Override
	public String displayStatistics() {
		
		this.saveInFile(this.topSeller.getSales(), 0, 0);
	
		String result = "** Auctioneer - " + super.name + "**\nTotal Auction Sales: ";
		double autoCar = 0;
		double manualCar = 0;
		for(Entry<Advert, Buyer> entry: this.soldCars.entrySet()) {
			
			switch (entry.getKey().getCars().getGearbox()) {
			case AUTOMATIC: autoCar++;
			break;
			default: manualCar++;
			break;
			}
			
		}
		double percentageAuto = (autoCar / this.soldCars.size())*100;
		double percentageManual = (manualCar / this.soldCars.size())*100;
		double totalCars = autoCar + manualCar;
		DecimalFormat df = new DecimalFormat("0");
		
		result +=  df.format(totalCars) + "\t Automatic Cars: " + percentageAuto + "%\t Manual Cars: " + percentageManual +
				"%\nTop Seller: " + this.topSeller.toString() + "\n";		
		return result;
	}
	
	
	
	/**
	 * This method will utilize the BufferedWriter to write the statistics of the sale in a text file.
	 * The number of automatic and manual cars and their percentage is defined similar to the method above (displayStatistics)
	 * by using the switch case to increment the number of cars, with the relevant type of gearbox, which were sold.
	 * 
	 *  this data is then written in the 'aucion_statistics' text file.
	 *
	 * If any errors do appear when writing in the text file, an IOException will be caught using the try catch statement
	 * 
	 * @param noOfSales
	 * @param percentageOfUsed
	 * @param percentageOfNew
	 * @throws IOException
	 */
	private void saveInFile(int noOfSales, double percentageOfUsed, double percentageOfNew) {
		
		String result = "";
		double autoCar = 0;
		double manualCar = 0;
		for(Entry<Advert, Buyer> entry: this.soldCars.entrySet()) {

			switch (entry.getKey().getCars().getGearbox()) {
			case AUTOMATIC: autoCar++;
			break;
			default: manualCar++;
			break;
			}
			
		}
		
		double percentageAuto = (autoCar / this.soldCars.size())*100;
		double percentageManual = (manualCar / this.soldCars.size())*100;
		double totalCars = autoCar + manualCar;
		DecimalFormat df = new DecimalFormat("0");
		
		/**This whole string called 'result' will then be written in the text file inside the 'try' statement */
		result += "Total Auction Sales: " +  df.format(totalCars) + " Automatic Cars: " + percentageAuto + "% Manual Cars: " + percentageManual +
				"%\nTop Seller: " + this.topSeller.toString() + "\n";		
		
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter ("auction_statistics.txt"));
			
			bufferedWriter.write(result);
			
			bufferedWriter.close();
			
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * This method will check if the seller has the most amount of sales compared to the other sellers, then this specific seller
	 * will be updated as the top seller.
	 * 
	 * if that seller is just a regular seller (not a top seller) then they will be added to the 'sales' hash map along side the amount 
	 * of sales for that specific seller
	 * 
	 * @param seller
	 */
	private void updateStatistics(Seller seller) {
		for(Map.Entry<Seller, Integer> entry: this.sales.entrySet()) {
			if(seller.getSales() > entry.getValue()) {
				this.topSeller = seller;
			}
		} 
		if(this.topSeller == null) {
			this.topSeller = seller;
		}
		this.sales.put(seller, seller.getSales());
		
	}
	
}
