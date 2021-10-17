package coursework_question4;

/**
 * This class is an abstract class that will define the basic structure for 2 methods that will then be extended by
 * Seller and Buyer classes where the body of the methods will be written.
 *   
 * @author Mouaz Abdelsamad
 *
 */
public abstract class User {
	
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
	 * This is a simple getter method that returns the fullname of the user
	 */
	public String getFullname() {
		return this.fullname;
	}
	
	
	/**
	 *The following abstract methods define the structure of each method that will be used later in the buyer and the seller class
	 */
	public abstract String getName();
	
	@Override
	public abstract String toString();
	
}
