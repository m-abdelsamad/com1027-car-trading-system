package coursework_question3;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class DealershipJTest {
	Dealership dealership = null;

	@Test
	public void coursework_testDealershipAuction() {
		dealership = new Auctioneer("Auto Auction");
	}

	@Test
	public void coursework_testDealershipTrader() {
		dealership = new Trader("AutoTrader");
	}

	@Test
	public void coursework_testDisplaySoldCarsTrader() {
		dealership = new Trader("AutoTrader");

		Car car3 = new Car(3456, "Nissan Micra", 7000, Condition.NEW, SaleType.FORSALE);
		Car car4 = new Car(1234, "Mercedes", 10000, Condition.USED, SaleType.FORSALE);

		Advert ad3 = new Advert(car3);
		Advert ad4 = new Advert(car4);

		Buyer user = new Buyer("Bob Ross", 18);
		Buyer user2 = new Buyer("Alice Wonderland", 50);

		Seller seller2 = new Seller("Adam Hills");

		dealership.registerCar(ad3, seller2, "Silver", CarType.AUTOMATIC, CarBody.MICRO, 4);
		dealership.registerCar(ad4, seller2, "Black", CarType.AUTOMATIC, CarBody.CABRIOLET, 4);

		dealership.placeOffer(ad3, user, 6000); // no
		dealership.placeOffer(ad3, user2, 6500); // no
		dealership.placeOffer(ad3, user, 8000);
		dealership.placeOffer(ad4, user2, 10000);

		assertThat(dealership.displaySoldCars(),
				anyOf(is("SOLD CARS:\n" + "1234 - Purchased by A***e with a successful £10000.00 bid. \n"
						+ "3456 - Purchased by B***b with a successful £8000.00 bid. \n" + ""),
						is("SOLD CARS:\n" + "3456 - Purchased by B***b with a successful £8000.00 bid. \n"
								+ "1234 - Purchased by A***e with a successful £10000.00 bid. \n" + "")));

	}

	@Test
	public void coursework_testDisplayStatistics() {
		dealership = new Trader("AutoTrader");
		assertEquals("Statistics", dealership.displayStatistics());
	}

	@Test
	public void coursework_testDisplayUnsoldCarsAuction() {
		Auctioneer dealership = new Auctioneer("Auto Auction");

		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		Car car2 = new Car(2345, "Toyota Corolla", 15000, Condition.NEW, SaleType.AUCTION);

		Advert ad = new Advert(car);
		Advert ad2 = new Advert(car2);

		User user2 = new Buyer("Alice Wonderland", 50);
		User seller = new Seller("Stella Kazamia");
		User seller2 = new Seller("Adam Hills");

		dealership.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		dealership.registerCar(ad2, seller2, "Red", CarType.MANUAL, CarBody.MICRO, 4);

		dealership.placeOffer(ad, user2, 20000);
		dealership.placeOffer(ad2, user2, 25); // no
		dealership.placeOffer(ad, user2, 20001);
		dealership.placeOffer(ad, user2, 20); // no

		dealership.endSale(ad);
		dealership.endSale(ad2);

		assertEquals(
				"UNSOLD CARS:\n" + "Ad: 2345 - Toyota Corolla (£15000.00)\n" + "	 Type: MANUAL\n"
						+ "	 Style: MICRO\n" + "	 Colour: Red\n" + "	 No. of Seats: 4\n" + "	 Condition: NEW\n" + "",
				dealership.displayUnsoldCars());

	}

	@Test
	public void coursework_testPlaceOffer() {
		Auctioneer dealership = new Auctioneer("Auto Auction");

		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.AUCTION);
		Car car2 = new Car(2345, "Toyota Corolla", 15000, Condition.NEW, SaleType.AUCTION);

		Advert ad = new Advert(car);
		Advert ad2 = new Advert(car2);

		User user2 = new Buyer("Alice Wonderland", 50);
		User seller = new Seller("Stella Kazamia");
		User seller2 = new Seller("Adam Hills");

		dealership.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		dealership.registerCar(ad2, seller2, "Red", CarType.MANUAL, CarBody.MICRO, 4);

		assertTrue(dealership.placeOffer(ad2, user2, 25));

	}

	@Test
	public void coursework_testPlaceOfferTrader() {
		dealership = new Trader("AutoTrader");

		Car car = new Car(1234, "Mazda 3", 20000, Condition.NEW, SaleType.FORSALE);
		Car car2 = new Car(2345, "Toyota Corolla", 15000, Condition.NEW, SaleType.FORSALE);

		Advert ad = new Advert(car);
		Advert ad2 = new Advert(car2);

		User user2 = new Buyer("Alice Wonderland", 50);
		User seller = new Seller("Stella Kazamia");
		User seller2 = new Seller("Adam Hills");

		dealership.registerCar(ad, seller, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
		dealership.registerCar(ad2, seller2, "Red", CarType.MANUAL, CarBody.MICRO, 4);

		assertFalse(dealership.placeOffer(ad2, user2, 250));

	}
	
}
