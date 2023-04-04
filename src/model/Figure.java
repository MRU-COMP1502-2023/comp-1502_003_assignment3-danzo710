package model;

/**
 * Figure subclass it inherits from the Toy Superclass
 * Contains all the unique figure attributes
 */
public class Figure extends Toys {
	//variable declaration
	char classification;

	/**
	 * Constructor for Figures takes the inputted values and sets them to the corresponding variables of the object
	 * Uses the superclass constructor to set the common values
	 * @param category The inputted category it is set to the superclass constructor
	 * @param name The inputted name it is set to the superclass constructor
	 * @param brand The inputted brand it is set to the superclass constructor
	 * @param price The inputted price it is set to the superclass constructor
	 * @param ageAppropriate The inputted age Appropriate it is set to the superclass constructor
	 * @param availableCount The inputted Available Count it is set to the superclass constructor
	 * @param classification The inputted classification unique attribute for figures it is set in this constructor
	 * @param serialNumber The inputted serial number it is set to the superclass constructor
	 */
	public Figure(String category, String name, String brand, double price, int ageAppropriate, int availableCount,
				  char classification, String serialNumber) {
		super(category, name, brand, price, ageAppropriate, availableCount, serialNumber);
		this.classification = Character.toUpperCase(classification);
	}

	/**
	 * toString is a method to show a string representation of our object
	 * Overrides the default toString method
	 * @return a properly formatted string representation for the object
	 */
	public String toString() {
		return "Category: Figure, Name: " + getName() + ", Made by: " + getBrand() + ", Price: " + getPrice() + "$, "
				+ "Number available: " + getAvailableCount() + ", Recommended age: " + getAgeAppropriate()
				+ ", figure Classification: " + classification + ", Serial Number: " + getSerialNumber();
	}

	/**
	 * Gets the current classification
	 * @return the current values of the classification variable
	 */
	public char getClassification() {
		return classification;
	}

	/**
	 * Setter Method for the classification variable
	 * @param classification new classification that will replace the current one
	 */
	public void setClassification(char classification) {
		this.classification = classification;
	}

}
