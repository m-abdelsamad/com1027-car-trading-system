package coursework_question1;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserJTest {

	User user = null;
	
	@Test
	public void coursework_testUserConstruction() {
		user = new User("Joe Bloggs");
	}
	@Test (expected=IllegalArgumentException.class)
	public void coursework_testInvalidUser() {
		user = new User("joe");
	}
	@Test
	public void coursework_testGetName() {
		user = new User("Alice Wonderland");
		assertEquals("Alice", user.getName());
	}

	@Test
	public void coursework_testToString() {
		user = new User("Bob Ross");
		assertEquals("Bob Ross", user.toString());
	}

}
