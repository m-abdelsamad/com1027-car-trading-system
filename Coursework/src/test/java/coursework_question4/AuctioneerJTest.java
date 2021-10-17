package coursework_question4;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuctioneerJTest {

	Auctioneer auctioneer = new Auctioneer("Cars Ltd");

	@Test
	public void coursework_testAuctioneer() {
		auctioneer = new Auctioneer("Adam Hills");
	}

	@Test
	public void coursework_testDisplayNoSoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		User seller = new Seller("Sid James");
		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		assertEquals("SOLD CARS:\n" + "", auctioneer.displaySoldCars());

	}

	@Test
	public void coursework_testDisplaySoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Stella Kazamia", 40);

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer, 20000);
		auctioneer.endSale(ad);
		assertEquals("SOLD CARS:\n" + "1234 - Purchased by S***a with a successful £20000.00 bid. \n" + "",
				auctioneer.displaySoldCars());

	}


	@Test
	public void coursework_testDisplayNoUnsoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		User seller = new Seller("Sid James");
		User buyer1 = new Buyer("Stella Kazamia", 40);
		User buyer2 = new Buyer("Bob Ross", 61);

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer1, 19999.99);
		auctioneer.placeOffer(ad, buyer2, 21000);
		auctioneer.endSale(ad);
		assertEquals("UNSOLD CARS:\n" + "", auctioneer.displayUnsoldCars());
	}

	@Test
	public void coursework_testDisplayUnsoldCars() {
		Car car = new Car(83, "Vauxhal", 1500, Condition.USED, SaleType.AUCTION);
		User seller = new Seller("Stella Kazamia");
		User buyer = new Buyer("Sid James", 40);

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer, 1000);
		auctioneer.placeOffer(ad, buyer, 1400);
		auctioneer.endSale(ad);
		assertEquals(
				"UNSOLD CARS:\n" + "Ad: 83 - Vauxhal (£1500.00)\n" + "	 Type: AUTOMATIC\n" + "	 Style: HATCHBACK\n"
						+ "	 Colour: Blue\n" + "	 No. of Seats: 5\n" + "	 Condition: USED\n" + "",
				auctioneer.displayUnsoldCars());
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidEndSale() {
		auctioneer.endSale(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidRegister() {
		auctioneer.registerCar(null, null, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidPlaceOffer() {
		auctioneer.placeOffer(null, null, 19999.99);
		auctioneer.endSale(null);
	}

	@Test
	public void coursework_testPlaceOffer() {
		Car car = new Car(8907, "Mazda 3", 4000, Condition.USED, SaleType.AUCTION);
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Stella Kazamia", 40);

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);

		assertTrue(auctioneer.placeOffer(ad, buyer, 4005));
	}
	
	@Test
	public void coursework_testFileStatistics() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		Car car2 = new Car(2345, "Toyota Corolla", 15000, Condition.NEW, SaleType.AUCTION);
		
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Stella Kazamia", 40);

		Advert ad = new Advert(car);
		Advert ad2 = new Advert(car2);
		
		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.registerCar(ad2, seller, "Red", CarType.MANUAL, CarBody.MICRO, 4);
		
		auctioneer.placeOffer(ad, buyer, 20000);
		auctioneer.placeOffer(ad2, buyer, 15001);
		auctioneer.endSale(ad);
		auctioneer.endSale(ad2);
		
		assertEquals("** Auctioneer - Cars Ltd**\n" + 
				"Total Auction Sales: 2	 Automatic Cars: 50.0%	 Manual Cars: 50.0%\n" + 
				"Top Seller: Sid J. (Sales: 2, Rating: Level 1)\n" + 
				"", auctioneer.displayStatistics());
	}

}
