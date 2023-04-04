package model;
/**
 * Board games subclass it inherits from the Toy Superclass
 * Contains all the unique board games attributes
 */
public class Animals extends Toys {
	// Variable declaration
	char size;
	String material;

	/**
	 * Constructor for animals takes the inputted values and sets them to the corresponding variables of the object
	 * Uses the superclass constructor to set the common values
	 * @param category The inputted category it is set to the superclass constructor
	 * @param name The inputted name it is set to the superclass constructor
	 * @param brand The inputted brand it is set to the superclass constructor
	 * @param price The inputted price it is set to the superclass constructor
	 * @param ageAppropriate The inputted age Appropriate it is set to the superclass constructor
	 * @param availableCount The inputted Available Count it is set to the superclass constructor
	 * @param serialNumber The inputted serial number it is set to the superclass constructor
	 * @param size The inputted size unique attribute for animals it is set in this constructor
	 * @param material The inputted material unique attribute for animals it is set in this constructor
	 */
	public Animals(String category, String name, String brand, double price, int ageAppropriate, int availableCount,
				   String serialNumber, char size, String material) {
		super(category, name, brand, price, ageAppropriate, availableCount, serialNumber);
		this.material = material;
		this.size = size;
	}

	/**
	 * toString is a method to show a string representation of our object
	 * Overrides the default toString method
	 * @return a properly formatted string representation for the object
	 */
	public String toString() {
		return "Category: Animal, Name: " + getName() + ", Made by: " + getBrand() + ", Price: " + getPrice() + "$, "
				+ "Number available: " + getAvailableCount() + ", Recommended age: " + getAgeAppropriate() + ", Size: " + size
				+ ", Material Used: " + material + ", Serial Number: " + getSerialNumber();
	}


	/**
	 * Gets the current size
	 * @return the current values of the size variable
	 */
	public char getSize() {
		return size;
	}

	/**
	 * Setter Method for the size variable
	 * @param size new max that will replace the current one
	 */
	public void setSize(char size) {
		this.size = size;
	}

	/**
	 * Gets the current material
	 * @return the current values of the material variable
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Setter Method for the material variable
	 * @param material new material that will replace the current one
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}
