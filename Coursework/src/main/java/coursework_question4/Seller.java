package coursework_question4;

/**
 * This class extends the functionality of the User abstract class, and will override the 
 * abstract methods from the User class by writing the relevant code in the body of each method.
 *  
 * @author Mouaz Abdelsamad
 *
 */
public class Seller extends User {

	/**
	 * This field represents the number of sales by each seller
	 */
	private int sales;
	
	/**
	 * This constructor initializes the 'fullname' of the seller.
	 * the 'fullname' field is called (inherited) from the User class using 'super' 
	 * @param fullname
	 */
	public Seller(String fullname) {
		super(fullname);
	}
	
	/**
	 * this constructor initializes the number of sales made by each seller, and initializes the 'fullname' of the seller 
	 * @param fullname
	 * @param sales
	 */
	public Seller(String fullname, int sales) {
		super(fullname);
		this.sales = sales;
	}
	
	/**
	 * this to string method outputs the 'fullname' in a specified format along side the 'sale level' for each seller.
	 * to perform such format I have created an new array that uses the 'split' function which initializes the zero index with the first name
	 * and the first index with the last name (initial). 
	 * This method also calls the identifyRating method to display the 'sale level' info   
	 */	
	@Override
	public String toString() {
		String[] name = super.getFullname().split(" ");
		String fName = name[0];
		String lName = name[1];
		String initial = lName.charAt(0) + ". ";
		return fName + " " + initial + "(Sales: " + this.sales + ", Rating: " + this.identifyRating() + ")";
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
	
	/**
	 * Simple getter method that retrieves the number of sales
	 * @return sales
	 */
	public int getSales() {
		return this.sales;
	}
	
	/**
	 * Simple setter method that sets the number of sales
	 * @param sales
	 */
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
	/**
	 * This method checks the number of sales and outputs the relevant data using a switch case.
	 * the 'outputed' data is dependent on the 'sales' field
	 */
	public String identifyRating() {
		String result = "";
		switch (this.sales) {
		case 0: result += "Level 0";
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5: result += "Level 1";
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10: result += "Level 2";
			break;
		default: result += "Level 3";
			break;
		}
		return result;
	}

	
}
