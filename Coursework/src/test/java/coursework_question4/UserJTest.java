package coursework_question4;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserJTest {

	User user = null;

	@Test
	public void coursework_testBuyerConstruction() {
		Buyer user = new Buyer("Joe Bloggs", 21);
		assertEquals("Joe", user.getName());
		assertEquals(21, user.getAge());

	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidBuyer() {
		user = new Buyer("Alice Wonderland", 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coursework_testInvalidSeller() {
		user = new Seller("joE");
	}

	@Test
	public void coursework_testGetName() {
		user = new Seller("Alice Wonderland");
		assertEquals("Alice", user.getName());
	}

	@Test
	public void coursework_testBuyerToString() {
		user = new Buyer("Mariyah Ross", 20);
		assertEquals("M***h", user.toString());
	}

	@Test
	public void coursework_testSellerToString() {
		user = new Seller("Bob Ross");
		assertEquals("Bob R. (Sales: 0, Rating: Level 0)", user.toString());
	}
}
