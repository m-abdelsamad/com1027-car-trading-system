package coursework_question1;

public class User {
	
	/**
	 * This field represents the fullname of the user
	 */
	private String fullname;
	
	/**
	 * defined here is a regular expression to check if the fullname matches the format of this expression
	 */
	String regExp = "([A-Z][a-z]*) ([A-Z][a-z]*)";
	
	
	/**
	 * This constructor initializes the fullname of the user, if the fullname doesn't match the regular expression defined above,
	 * or if the fullname is null, it will throw an IllegalArgumentException
	 * 
	 * @param fullname
	 * @throws IllegalArgumentException
	 */
	public User(String fullname) throws IllegalArgumentException {
		
		if(!fullname.matches(regExp) || fullname == null) {
			throw new IllegalArgumentException ("name should start with capital letter"); 
		} this.fullname = fullname;
		
	}

	/**
	 * This getter will return only the first name of the fullname
	 * To do so I have created an new array that uses the 'split' function, then i have initialized the zero index with the first name. 
	 */
	public String getName() {
		String[] FName = this.fullname.split(" ");
		return FName[0];
	}


	/**
	 * This is a simple toString method that returns the fullname of the user
	 */
	@Override
	public String toString() {
		return this.fullname;
	}
	
	
}
