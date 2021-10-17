package coursework_question4;

import static org.junit.Assert.*;

import org.junit.Test;

public class TraderJTest {

	Trader trader = new Trader("AutoTrader");

	@Test
	public void coursework_testTrader() {
		trader = new Trader("Adam Hills");
	}

	@Test
	public void coursework_testDisplayNoSoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new Seller("Sid James");
		Advert ad = new Advert(car);

		trader.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		assertEquals("SOLD CARS:\n" + "", trader.displaySoldCars());

	}

	@Test
	public void coursework_testDisplaySoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Stella Kazamia", 40);

		Advert ad = new Advert(car);

		trader.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		trader.placeOffer(ad, buyer, 20000);
		assertEquals("SOLD CARS:\n" + "1234 - Purchased by S***a with a successful £20000.00 bid. \n" + "",
				trader.displaySoldCars());
		System.out.println(trader.displaySoldCars());

	}

	@Test
	public void coursework_testDisplayNoUnsoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new Seller("Sid James");
		User buyer1 = new Buyer("Stella Kazamia", 40);
		User buyer2 = new Buyer("Bob Ross", 21);

		Advert ad = new Advert(car);

		trader.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		trader.placeOffer(ad, buyer2, 21000);
		assertEquals("UNSOLD CARS:\n" + "", trader.displayUnsoldCars());
	}

	@Test
	public void coursework_testDisplayUnsoldCars() {
		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Stella Kazamia", 80);

		Advert ad = new Advert(car);

		trader.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		trader.placeOffer(ad, buyer, 15000);
		assertEquals("UNSOLD CARS:\n", trader.displayUnsoldCars());
		System.out.println(trader.displayUnsoldCars());
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidRegister() {
		trader.registerCar(null, null, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidPlaceOffer() {
		trader.placeOffer(null, null, 19999.99);
	}

	@Test
	public void coursework_testPlaceOffer() {
		Car car = new Car(8907, "Mazda 3", 4000, Condition.USED, SaleType.FORSALE);
		User seller = new Seller("Sid James");
		User buyer = new Buyer("Nancy Health", 19);

		Advert ad = new Advert(car);

		trader.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);

		assertTrue(trader.placeOffer(ad, buyer, 4005));
	}


	@Test
	public void coursework_testFileStatistics() {
		Car car6 = new Car(1234, "Mazda 3", 2000, Condition.USED, SaleType.FORSALE);
		Car car7 = new Car(2345, "Chevrolet", 4500, Condition.USED, SaleType.FORSALE);
		Car car8 = new Car(6789, "Toyota Auris", 2000, Condition.USED, SaleType.FORSALE);
		Car car9 = new Car(0123, "Vauxhal", 1500, Condition.USED, SaleType.FORSALE);

		User seller = new Seller("Sid James");
		User seller2 = new Seller("Alice Wonderland");
		User buyer = new Buyer("Bob Ross", 40);

		Advert ad6 = new Advert(car6);
		Advert ad7 = new Advert(car7);
		Advert ad8 = new Advert(car8);
		Advert ad9 = new Advert(car9);

		trader.registerCar(ad6, seller2, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		trader.registerCar(ad7, seller, "Red", CarType.MANUAL, CarBody.MICRO, 4);
		trader.registerCar(ad8, seller, "Black", CarType.MANUAL, CarBody.PICKUP, 2);
		trader.registerCar(ad9, seller, "White", CarType.MANUAL, CarBody.SEDAN, 8);

		trader.placeOffer(ad6, buyer, 2000);
		trader.placeOffer(ad7, buyer, 4500);
		trader.placeOffer(ad8, buyer, 2000);

		assertEquals(
				"** Trader - AutoTrader**\n" + "Total Sales: 3\n" + "All Sellers:\n"
						+ "	Alice W. (Sales: 1, Rating: Level 1)\n" + "	Sid J. (Sales: 2, Rating: Level 1)\n" + "",
				trader.displayStatistics());
	}
}
