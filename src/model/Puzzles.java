package model;

/**
 * Puzzle subclass it inherits from the Toy Superclass
 * Contains all the unique puzzle attributes
 */
public class Puzzles extends Toys {
	//unique subclass attributes
	String puzzleType;

	/**
	 * Constructor for Puzzles takes the inputted values and sets them to the corresponding variables of the object
	 * Uses the superclass constructor to set the common values
	 * @param category The inputted category it is set to the superclass constructor
	 * @param name The inputted name it is set to the superclass constructor
	 * @param brand The inputted brand it is set to the superclass constructor
	 * @param price The inputted price it is set to the superclass constructor
	 * @param ageAppropriate The inputted age Appropriate it is set to the superclass constructor
	 * @param availableCount The inputted Available Count it is set to the superclass constructor
	 * @param puzzletype The inputted Puzzle type unique attribute for puzzles it is set in this constructor
	 * @param serialNumber The inputted serial number it is set to the superclass constructor
	 */
	public Puzzles(String category, String name, String brand, double price, int ageAppropriate, int availableCount,
				   String puzzletype, String serialNumber) {
		super(category, name, brand, price, ageAppropriate, availableCount, serialNumber);
		this.puzzleType = puzzletype;
	}

	/**
	 * toString is a method to show a string representation of our object
	 * Overrides the default toString method
	 * @return a properly formatted string representation for the object
	 */
	public String toString() {
		return "Category: Puzzle, Name: " + getName() + ", Made by: " + getBrand() + ", Price: " + getPrice() + "$, "
				+ "Number available: " + getAvailableCount() + ", Recommended age: " + getAgeAppropriate() + ", Type of Puzzle: "
				+ puzzleType + ", Serial Number: " + getSerialNumber();
	}

	/**
	 * Gets the current puzzle Type
	 * @return the current values of the puzzle type variable
	 */
	public String getPuzzleType() {
		return puzzleType;
	}

	/**
	 * Setter Method for the puzzle type
	 * @param puzzleType new puzzle type that will replace the current one
	 */
	public void setPuzzleType(String puzzleType) {
		this.puzzleType = puzzleType;
	}

}
