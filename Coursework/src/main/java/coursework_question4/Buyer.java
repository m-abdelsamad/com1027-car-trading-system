package coursework_question4;

/**
 * This class extends the functionality of the User abstract class, and will override the 
 * abstract methods from the User class by writing the relevant code in the body of each method.
 *  
 * @author Mouaz Abdelsamad
 *
 */
public class Buyer extends User {
	
	/** This field represents the age of the buyer*/
	private int age;
	
	
	/** This constructor allows the age and fullname (which is being inherited from the User Class via 'super')
	 * to be initialized.
	 * If the age is below 18 (accepted age) the object will not be created and an IllegalArgumentException will be thrown
	 *
	 * @throws IllegalArgumentException 
	 */
	public Buyer(String fullname, int age) throws IllegalArgumentException {
		super(fullname);
		if(age < 18) {
			throw new IllegalArgumentException();
		}
		this.age = age;
	}

	/**This is a simple getter method that will retrieve the age of the Buyer 
	 * @return the age of the buyer
	 */
	public int getAge() {
		return this.age;
	}

	
	/**This toString method is defined to return the fullname in a specified format.
	 * the format is represented by having the First and last letter of the name to be displayed
	 *  while the rest of letters are hidden with 3 stars
	 *  
	 *  To construct such format, I have defined 3 local variables. These local variables are used to get a 
	 *  specific letter from the name by using the 'charAt' function. 
	 */
	@Override
	public String toString() {
		
		String name = this.getName();
		int lastLetterIndex = (name.length()) - 1;
		char firstLetter = name.charAt(0);
		char lastLetter = name.charAt(lastLetterIndex);
		
		return firstLetter + "***" + lastLetter;
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
