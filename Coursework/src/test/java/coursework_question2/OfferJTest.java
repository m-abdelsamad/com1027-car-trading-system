package coursework_question2;

import static org.junit.Assert.*;

import org.junit.Test;

public class OfferJTest {

	@Test
	public void coursework_testOffer() {
		User buyer = new User("Adam Hills");
		Offer offer = new Offer(buyer, 10);

		assertEquals("Adam Hills", offer.getBuyer().toString());
	}

	@Test (expected =IllegalArgumentException.class)
	public void coursework_testInvalidOffer() {
		Offer offer = new Offer(null, 10);
	}

	@Test (expected =IllegalArgumentException.class)
	public void coursework_testInvalidOfferValue() {
		User buyer = new User("Mariyah Khan");
		Offer offer = new Offer(buyer, -1);
	}

	@Test
	public void coursework_testToString() {
		User buyer = new User("Mariyah Khan");
		Offer offer = new Offer(buyer, 10000);	
		assertEquals("Mariyah Khan offered £10000.0", offer.toString());
	}

}
