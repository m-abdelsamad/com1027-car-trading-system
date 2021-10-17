package coursework_question3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AdvertTest {
	
	/**
	 * This method will test if an 'Advert' object is created
	 */
	@Test
	public void test_Constructor() {
		Car car = new Car (2345, "Toyota Corolla", 7000, Condition.USED, SaleType.FORSALE);
		Advert ad = new Advert(car);
	}
	
	/**
	 * This method will test the output of the toString
	 */
	@Test
	public void test_toString() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		Advert ad = new Advert(car);
		
		car.setBody(CarBody.HATCHBACK);
		car.setColour("White");
		car.setGearbox(CarType.MANUAL);
		car.setNumberOfSeats(4);
		
		
		assertEquals("Ad: 1234 - Mazda 3 (£20000.00)\n" + "	 Type: MANUAL\n" + "	 Style: HATCHBACK\n"
				+ "	 Colour: White\n" + "	 No. of Seats: 4\n" + "	 Condition: NEW", ad.toString());
		
	}
	
	/**
	 * This method will see if a car object is returned using the getCars method
	 */
	@Test
	public void test_getCars() {
		
		Car car = new Car (2345, "Toyota Corolla", 7000, Condition.USED, SaleType.FORSALE);
		Advert ad = new Advert(car);
		
		assertEquals(car, ad.getCars());
	}
	
	/**
	 * this will test the getHighestOffer method
	 */
	@Test
	public void test_getHighestOffer() {

		User user = new Buyer("Joe Bloggs", 25);
		User user1 = new Buyer("Alice Wonderland", 30);
		User user2 = new Buyer("Bob Ross", 27);
		
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		
		Advert ad = new Advert(car);
		
		ad.placeOffer(user, 21000);
		ad.placeOffer(user1, 23000);
		ad.placeOffer(user2, 27000);
		
		assertEquals(ad.getOffers().get(2), ad.getHighestOffer());
		
	}
	
	/**
	 * This test method will check if an offer is added to the 'offers' list (ie. placing an offer)
	 */
	@Test
	public void test_placeOffer() {
		User user = new Buyer("Joe Bloggs", 25);
		
		Car car = new Car(1234, "Mazda 3", 25000, Condition.NEW, SaleType.FORSALE);
		Advert ad = new Advert(car);
				
		assertEquals(true, ad.placeOffer(user, 25000));
	}
	
	
	/**
	 * This test method will create an invalid 'Advert' object 'NULL OBJECT' and will test if the NullPointerException 
	 * is thrown
	 */
	@Test (expected=NullPointerException.class)
	public void test_InvalidAdvertObject() {
		Advert ad = new Advert(null);
	}
	
}
