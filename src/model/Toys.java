package model;
/**
 * Toys superclass contains the common methods for all subclasses
 * Contains all the common toy attributes
 */
public abstract class Toys {
	//Variable declaration
	private String name;
	private String brand;
	private String serialNumber;
	private String category;
	private double price;
	private int availableCount;
	private int ageAppropriate;

	/**
	 * Super Class constructor sets the values of the parameters to the variables
	 * @param category The category of the toy Animal, Figure etc.
	 * @param name Name of the toy
	 * @param brand The brand of the toy
	 * @param price The price for the today it is a float
	 * @param ageAppropriate The appropriate age for the toy
	 * @param availableCount The current number of toys available
	 * @param serialNumber The serial number for the toy it is unique
	 */
	public Toys(String category, String name, String brand, double price, int ageAppropriate, int availableCount, String serialNumber){
		this.name = name;
		this.brand = brand;
		this.category = category;
		this.serialNumber = serialNumber;
		this.ageAppropriate = ageAppropriate;
		this.availableCount = availableCount;
		this.price = price;
	}

	/**
	 * Gets the current category
	 * @return the current values of the category variable
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter Method for the category variable
	 * @param category new category that will replace the current one
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the current name
	 * @return the current values of the name variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter Method for the name variable
	 * @param name new name that will replace the current one
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the current brand
	 * @return the current values of the brand variable
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Setter Method for the brand variable
	 * @param brand new brand that will replace the current one
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the current price
	 * @return the current values of the price variable
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter Method for the price variable
	 * @param price new price that will replace the current one
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the current available count
	 * @return the current values of the availableCount variable
	 */
	public int getAvailableCount() {
		return availableCount;
	}

	/**
	 * Setter Method for the available count variable
	 * @param availableCount new available count that will replace the current one
	 */
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	/**
	 * Gets the current serial number
	 * @return the current values of the serialNumber variable
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Setter Method for the serial number variable
	 * @param serialNumber new serial number that will replace the current one
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the current age Appropriate
	 * @return the current values of the ageAppropriate variable
	 */
	public int getAgeAppropriate() {
		return ageAppropriate;
	}

	/**
	 * Setter Method for the age appropriate variable
	 * @param ageAppropriate new age appropriate that will replace the current one
	 */
	public void setAgeAppropriate(int ageAppropriate) {
		this.ageAppropriate = ageAppropriate;
	}

}
