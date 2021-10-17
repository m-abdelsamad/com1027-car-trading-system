package coursework_question3;

import java.util.ArrayList;
import java.util.List;

public class Advert {
	
	/**
	 * the following fields represent a 'car' complex object and a List that contains the offers made for each car
	 */
	private Car car;
	private List<Offer> offers;
	
	
	/**
	 * this constructor initializes the 'car' complex object and initializes the list as an array list.
	 * if the car object is null, a NullPointerException is thrown
	 * @param car
	 * @throws NullPointerException
	 */
	public Advert(Car car) throws NullPointerException{
		if(car == null) {
			throw new NullPointerException ("Values of the Car cant be null");
		} this.car = car;
		
		this.offers = new ArrayList<Offer>();
	}

	
	/**
	 * (i have added this method myself, it was not mentioned in the UML diagram)
	 * this method was created for the purposes of returning a car object when ever it is called
	 * @return
	 */
	public Car getCars() {
		return this.car;
	}
		
	
	/**
	 * This method will return the offers placed on a car
	 * @return
	 */
	public List<Offer> getOffers(){
		return this.offers;
	}

	
	/**
	 * this method will return the highest offer placed by a buyer.
	 * This is done by looping through the offers list and extracting the value of that offer (highest offer)
	 * @return
	 */
	public Offer getHighestOffer() {
		int highest = 0;
		double max = 0;
		for(int i = 0; i < offers.size(); i++) {
			if(offers.get(i).getValue() > max) {
				max = offers.get(i).getValue();
				highest = i;
			}
		
		}
		return offers.get(highest);
	}
	
	/**
	 * This method will check if there is no is offer made for a car previously, if that is the case
	 * it will place a new offer and add it to the offers list
	 * @param buyer
	 * @param value
	 * @return
	 */
	public boolean placeOffer(User buyer, double value) {
		Offer offer = new Offer(buyer, value);
		if(!offers.contains(offer)) {
			this.offers.add(offer);
			return true;
		}
		return false;
	}

	
	/**
	 * this method will return the car specifications as an ad.
	 * the specifications of the car are retrieved through the complex object 'car'
	 */
	@Override
	public String toString() {
		return "Ad: " + this.car.displayCarSpecification(); 
	}
	
	
	
	
	
}
