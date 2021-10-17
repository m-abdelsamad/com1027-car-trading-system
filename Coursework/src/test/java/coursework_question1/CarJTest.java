package coursework_question1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarJTest {

	Car car = null;

	@Test
	public void coursework_testCarConstruction() {
		car = new Car(2345, "Toyota Corolla", 7000, Condition.USED);
		assertEquals(2345, car.getID());
		assertEquals("Toyota Corolla", car.getName());
		assertEquals(7000, car.getPrice(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidCarID() {
		car = new Car(-10, "Mazda MX5", 20000, Condition.NEW);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidCarName() {
		car = new Car(1234, null, 20000, Condition.NEW);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidCarPrice() {
		car = new Car(1234, "Mazda MX5", -500, Condition.NEW);
	}

	@Test
	public void coursework_testSetCarProperties() {
		car = new Car(3456, "Toyota Corolla", 7000, Condition.USED);

		car.setBody(CarBody.HATCHBACK);
		car.setColour("Blue");
		car.setGearbox(CarType.AUTOMATIC);
		car.setNumberOfSeats(5);

		assertEquals(3456, car.getID());
		assertEquals("Toyota Corolla", car.getName());
		assertEquals(7000, car.getPrice(), 0);
		assertEquals(CarBody.HATCHBACK, car.getBodyStyle());
		assertEquals("Blue", car.getColour());
		assertEquals(CarType.AUTOMATIC, car.getGearbox());
		assertEquals(5, car.getNumberOfSeats());
	}

	@Test
	public void coursework_testDisplayCarSpecification() {
		car = new Car(1234, "Mazda MX5", 20000, Condition.NEW);

		car.setBody(CarBody.SUPERCAR);
		car.setColour("White");
		car.setGearbox(CarType.MANUAL);
		car.setNumberOfSeats(2);

		assertEquals(
				"1234 - Mazda MX5 (£20000.00)\n" + "	 Type: MANUAL\n" + "	 Style: SUPERCAR\n"
						+ "	 Colour: White\n" + "	 No. of Seats: 2\n" + "	 Condition: NEW",
				car.displayCarSpecification());

	}

	@Test
	public void coursework_testToString() {
		car = new Car(2345, "Toyota Corolla", 15000, Condition.NEW);

		car.setBody(CarBody.MICRO);
		car.setColour("Red");
		car.setGearbox(CarType.MANUAL);
		car.setNumberOfSeats(4);

		assertEquals("2345 - Toyota Corolla", car.toString());
	}

}
