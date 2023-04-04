package model;

/**
 * Board games subclass it inherits from the Toy Superclass
 * Contains all the unique board games attributes
 */
public class BoardGames extends Toys {
	// variable declaration
	String designers;
	int min;
	int max;

	/**
	 * Constructor for board games takes the inputted values and sets them to the corresponding variables of the object
	 * Uses the superclass constructor to set the common values
	 * @param category The inputted category it is set to the superclass constructor
	 * @param name The inputted name it is set to the superclass constructor
	 * @param brand The inputted brand it is set to the superclass constructor
	 * @param price The inputted price it is set to the superclass constructor
	 * @param ageAppropriate The inputted age Appropriate it is set to the superclass constructor
	 * @param availableCount The inputted Available Count it is set to the superclass constructor
	 * @param designers The inputted designers unique attribute for board games it is set in this constructor
	 * @param min The inputted min number of players unique attribute for board games it is set in this constructor
	 * @param max The inputted max number of players unique attribute for board games it is set in this constructor
	 * @param serialNumber The inputted serial number it is set to the superclass constructor
	 */
	public BoardGames(String category, String name, String brand, double price, int ageAppropriate, int availableCount,
					  String designers, int min, int max, String serialNumber) {
		super(category, name, brand, price, ageAppropriate, availableCount, serialNumber);
		this.designers = designers;
		this.min = min;
		this.max = max;
	}

	/**
	 * toString is a method to show a string representation of our object
	 * Overrides the default toString method
	 * @return a properly formatted string representation for the object
	 */
	public String toString() {
		return "Category: Board Game, Name: " + getName() + ", Made by: " + getBrand() + ", Price: " + getPrice() + "$, "
				+ "Number available: " + getAvailableCount() + ", Recommended age: " + getAgeAppropriate() + ", Game Designers: "
				+ designers + ", Number of Players: " + min + "-" + max + ", Serial Number: " + getSerialNumber();
	}
	/**
	 * Gets the current designers
	 * @return the current values of the designers variable
	 */
	public String getDesigners() {
		return designers;
	}

	/**
	 * Setter Method for the designers variable
	 * @param designers new designers that will replace the current one
	 */
	public void setDesigners(String designers) {
		this.designers = designers;
	}
	/**
	 * Gets the current min
	 * @return the current values of the min variable
	 */
	public int getMin() {
		return min;
	}
	/**
	 * Setter Method for the min variable
	 * @param min new min that will replace the current one
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/**
	 * Gets the current max
	 * @return the current values of the max variable
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Setter Method for the max variable
	 * @param max new max that will replace the current one
	 */
	public void setMax(int max) {
		this.max = max;
	}
}