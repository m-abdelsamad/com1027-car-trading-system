package coursework_question4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
	 * Once a sale ends, the car sold will be added to the 'soldCars' hash map and will be removed from the 'carsForSale' hash map 
	 * indicating that the car is no longer for sale.
	 * when a car is sold, the number of sales will change and therefore the seller of that specific car will be added to the 
	 * sellers list, this will be done by called the private method 'updateStatistis'
	 * 
	 * if the Advert object is null, an IllegalArgumentException is thrown
	 * @param advert
	 * @throws IllegalArgumentException
	 */
	private void endSale(Advert advert) throws IllegalArgumentException {
		if(advert == null) {
			throw new IllegalArgumentException("Values cant be null");
		}
		
		this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
		
		Seller seller = this.carsForSale.get(advert);
		seller.setSales(seller.getSales() + 1);
		if(!this.sellers.contains(seller)) {
			this.updateStatistics(seller);
		}
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
	 *This method will check if a car is available for sale and if an offer is placed.
	 *For a successful offer to be placed, the value of the offer must be higher than the existing offer. If this is 
	 *the case the endSale method will be called and the sold car will be removed from the 'carsForSale' hash map and 
	 *placed in the 'soldCars' hash map.
	 *
	 *if the carAdvert object is null, the method will throw a IllegalArgumentException
	 *
	 *@throwsIllegalArgumentException
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
			Buyer buyer = entry.getValue();
			result += entry.getKey().getCars().getID() + " - Purchased by " + buyer.toString() + " with a successful £"
					+ df.format(entry.getKey().getHighestOffer().getValue()) + " bid. \n" + "";
		}
		return result;
	}
	
	/**
	 *This method will display the Name of the trader, total sales, and the 'sales level' for each seller in a 
	 *specified format
	 *
	 *to order the 'sellers' list, i have defined a new list of type 'String', I have then added the data from the 
	 *sellers list to the new list I defined ('oderderedList'), finally since the data type of the new list is a String it will be 
	 *very easy to sort alphabetically by using the 'Collections.sort' function
	 *
	 *this method will also call the 'saveInFile' which will write the statistics of the sellers in a text file.
	 */
	@Override
	public String displayStatistics() {
		
		for(Seller seller: this.sellers) {
			this.saveInFile(seller.getSales());
		}
		
		List<String> orderedList = new ArrayList<String>();
		int totalSales = 0;
		
		/**
		 * This loop will first add all the 'sale levels' info to the new list I defined
		 * then it will add all the sales and store this value in the totalSales local variable
		 */
		for(int i =0; i< this.sellers.size(); i++) {
			orderedList.add(this.sellers.get(i).toString());
			totalSales += this.sellers.get(i).getSales();
		}
		
		String result = "** Trader - " + super.name + "**\nTotal Sales: " + totalSales 
				+ "\nAll Sellers:\n";
		
		String orderedResult = ""; //this local variable  will include the sorted data from the 'orderedList'
		
		Collections.sort(orderedList);
		for(int i = 0; i < orderedList.size(); i++) {
			orderedResult += "\t" + orderedList.get(i) + "\n" ;
		}
		
		//finally i concatenated the two local variables to get the required format 
		return result + orderedResult;		
	}
	
	
	/**
	 *This private method here will record the details of the amount of total sales, and the 'sale level' of each seller.
	 *This recorded info will be written/added to a text file, to do so, I have used the 'bufferedWritter' function.
	 *to get access to the information about the sale, I have used a foreach loop to step into each instance of the 'sellers' list
	 *and extract the info from the Sellers class. 
	 *
	 * The totalSales local variable will add up the number of all sales by all sellers
	 * The sellerInfo local variable contain the 'sale level' information about each seller
	 * 
	 * If any errors do appear when writing in the text file, an IOException will be thrown
	 * 
	 * @param noOfSales
	 * @throws IOException
	 */
	private void saveInFile(int noOfSales) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter ("trade_statistics.txt"));
			bufferedWriter.write("Total Sales: " + noOfSales + "\nAll Sellers:\n");
			
			String sellerInfo = "";
			for(Seller seller: this.sellers) {
				sellerInfo += "\t" + seller.toString() + "\n";
			}
			
			bufferedWriter.write(sellerInfo);
			bufferedWriter.close();
			
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method will update the seller info by adding it to the 'sellers' array list
	 * This method will be called in the endSale method
	 * 
	 * @param seller
	 */
	private void updateStatistics(Seller seller) {
		this.sellers.add(seller);
	}
	
}
