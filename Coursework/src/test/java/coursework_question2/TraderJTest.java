package coursework_question2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TraderJTest {


	Trader auctioneer = new Trader("Stella");

	@Test
	public void coursework_testTrader() {
		auctioneer = new Trader("Adam Hills");
	}

	@Test
	public void coursework_testDisplayNoSoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new User("Sid James");
		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		assertEquals("SOLD CARS:\n" + "", auctioneer.displaySoldCars());

	}

	@Test
	public void coursework_testDisplaySoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new User("Sid James");
		User buyer = new User("Stella Kazamia");

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer, 20000);
		assertEquals("SOLD CARS:\n" + "1234 - Purchased by Stella with a successful £20000.00 bid. \n" + "",
				auctioneer.displaySoldCars());
		System.out.println(auctioneer.displaySoldCars());

	}

	@Test
	public void coursework_testDisplayStatistics() {
		assertEquals("Statistics", auctioneer.displayStatistics());
	}

	@Test
	public void coursework_testDisplayNoUnsoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new User("Sid James");
		User buyer1 = new User("Stella Kazamia");
		User buyer2 = new User("Bob Ross");

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer2, 21000);
		assertEquals("UNSOLD CARS:\n" + "", auctioneer.displayUnsoldCars());
	}

	@Test
	public void coursework_testDisplayUnsoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new User("Sid James");
		User buyer = new User("Stella Kazamia");

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		auctioneer.placeOffer(ad, buyer, 19999.99);
		assertEquals(
				"UNSOLD CARS:\n" + "Ad: 1234 - Mazda 3 (£20000.00)\n" + "	 Type: AUTOMATIC\n" + "	 Style: HATCHBACK\n"
						+ "	 Colour: Blue\n" + "	 No. of Seats: 5\n" + "	 Condition: NEW\n" + "",
				auctioneer.displayUnsoldCars());
	System.out.println(auctioneer.displayUnsoldCars());
	}



	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidRegister() {
		auctioneer.registerCar(null, null, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidPlaceOffer() {
		auctioneer.placeOffer(null, null, 19999.99);
	}

	@Test
	public void coursework_testPlaceOffer() {
		Car car = new Car(8907, "Mazda 3", 4000, Condition.USED, SaleType.FORSALE);
		User seller = new User("Sid James");
		User buyer = new User("Stella Kazamia");

		Advert ad = new Advert(car);

		auctioneer.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);

		assertTrue(auctioneer.placeOffer(ad, buyer, 4005));
	}
}
