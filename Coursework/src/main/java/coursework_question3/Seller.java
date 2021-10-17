package coursework_question3;

/**
 * This class extends the functionality of the User abstract class, and will override the 
 * abstract methods from the User class by writing the relevant code in the body of each method.
 *  
 * @author Mouaz Abdelsamad
 *
 */
public class Seller extends User {

	
	/**
	 * This constructor initializes the 'fullname' of the seller.
	 * the 'fullname' field is called (inherited) from the User class using 'super' 
	 * @param fullname
	 */
	public Seller(String fullname) throws IllegalArgumentException {
		super(fullname);
	}
	
	/**
	 * this to string method outputs the 'fullname' in a specified format for each seller.
	 * to perform such format I have created an new array that uses the 'split' function which initializes the zero index with the first name
	 * and the first index with the last name (initial). 
	 */
	@Override
	public String toString() {
		String[] name = super.getFullname().split(" ");
		String fName = name[0];
		String lName = name[1];
		String initial = lName.charAt(0) + ". ";
		return fName + " " + initial + "()";
	}

	
	/**
	 * This getter will return only the first name of the fullname
	 * To do so I have created an new array that uses the 'split' function, then i have initialized the zero index with the first name. 
	 */
	@Override
	public String getName() {
		String[] name = super.getFullname().split(" ");
		return name[0];
	}
	
	

}
