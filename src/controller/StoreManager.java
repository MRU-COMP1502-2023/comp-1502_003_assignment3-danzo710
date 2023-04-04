package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import model.Animals;
import model.BoardGames;
import model.Figure;
import model.Puzzles;
import model.Toys;
import view.Menus;

/**
 * This class handles all the main navigation between classes Loads the text
 * file into an array list of toys Handles the logic for the user creating or
 * removing a toy Writes the array list with any new changes back to the txt
 * file Throws the exceptions for a negative price and for if the min is greater
 * than the max when creating a board game
 * 
 * @version SDK corretto-11
 * @author Daniel
 * @author Prince
 */
public class StoreManager {
	// Main variable declaration
	Menus M;
	final String FILE_PATH = "res/toys.txt";
	public static ArrayList<Toys> toyList = new ArrayList<Toys>();
	File toyFile = new File(FILE_PATH);
	Scanner keyboard = new Scanner(System.in);
	private char classification;

	/**
	 * Constructor of the class When ran creates a new menu object that has the
	 * current store manager
	 */
	public StoreManager() {
		M = new Menus(this);
	}

	/**
	 * Method that loads the file then stores it as an array list of toys
	 * 
	 * @throws FileNotFoundException If the file is not in the directory stops code
	 */
	public void loadFile() throws FileNotFoundException {
		Scanner inputFile = new Scanner(toyFile);
		inputFile.useDelimiter(";");
		String line;

		// will repeat code until the txt file does not have more lines
		while (inputFile.hasNext()) {
			// String representation of the current line in the file
			// Splits the line into an array of values at the ";"
			line = inputFile.nextLine();
			String[] values = line.split(";");

			// Sets variables to the corresponding values in the array
			String SerialNumber = values[0];
			String name = values[1];
			String brand = values[2];
			String category;
			double price = Double.parseDouble(values[3]);
			int availableCount = Integer.parseInt(values[4]);
			int ageAppropriate = Integer.parseInt(values[5]);
			char digit = SerialNumber.charAt(0);


			// Switch checks what is the first digit of serial number
			// If it's 0 or 1 sets the unique figure values, creates the figure object and
			// adds it to array list
			// If it's 2 or 3 sets the unique animal values, creates the animal object and
			// adds it to array list
			// If it's 4, 5 or 6 sets the unique puzzles values, creates the puzzle object
			// and adds it to array list
			// If it's 7, 8, or 9 sets the unique boardGame values, creates the boardGame
			// object and adds it to array list
			switch (digit) {
			case '0':
			case '1':
				category = "Figure";
				char classification = values[6].charAt(0);
				Figure Figure = new Figure(category, name, brand, price, ageAppropriate, availableCount, classification,
						SerialNumber);
				toyList.add(Figure);
				break;

			case '2':
			case '3':
				category = "Animal";
				String material = values[6];
				char size = values[7].charAt(0);
				Animals Animals = new Animals(category, name, brand, price, ageAppropriate, availableCount,
						SerialNumber, size, material);
				toyList.add(Animals);
				break;

			case '4':
			case '5':
			case '6':
				category = "Puzzle";
				String puzzleType = values[6];
				Puzzles Puzzle = new Puzzles(category, name, brand, price, ageAppropriate, availableCount, puzzleType,
						SerialNumber);
				toyList.add(Puzzle);
				break;

			case '7':
			case '8':
			case '9':
				category = "Board Game";
				String designers = values[7];
				String range = values[6];
				int min = Character.getNumericValue(range.charAt(0));
				int max = Character.getNumericValue(range.charAt(2));
				BoardGames BoardGames = new BoardGames(category, name, brand, price, ageAppropriate, availableCount,
						designers, min, max, SerialNumber);
				toyList.add(BoardGames);
				break;

			}

		}
		inputFile.close();
	}


	public ArrayList<Toys> searchGiftOptions(String stringAge, String category, String priceRange){
		ArrayList<Toys> giftList = new ArrayList<Toys>();
		int age = 0;
		double maxPrice = 99999999;
		double minPrice = 0;

		if(!stringAge.equals("")){
			age = Integer.parseInt(stringAge);
		}

		if(!priceRange.equals("")) {
			String[] priceRangeArray = priceRange.split("-");
			minPrice =   Double.parseDouble(priceRangeArray[0]);
			maxPrice = 	Double.parseDouble(priceRangeArray[1]);
		}

		for(Toys toy: toyList){
			if((category.equalsIgnoreCase(toy.getCategory()) || category.equals("")) && (age >= toy.getAgeAppropriate() || age == 0) && (minPrice <= toy.getPrice() && maxPrice >= toy.getPrice())){
				giftList.add(toy);
			}
		}
		return giftList;
	}

	/**
	 * This method checks if the available count is zero, if it is zero remove from
	 * the list. If the available count is greater than zero, this method minuses
	 * one from the toal available count.
	 * 
	 * @param wantedToy this is a toy that the user wants to purchase
	 * @throws IOException In case File is not found
	 */
	public boolean purchase(Toys wantedToy) throws IOException {
		boolean availableStock = true;

		for (Toys toy : toyList) {
			if (wantedToy.getAvailableCount() == 0) {
				availableStock = false;
				break;
			}
			if (wantedToy.equals(toy)) {
				toy.setAvailableCount(toy.getAvailableCount() - 1);
				wantedToy = toy;
				break;
			}
		}
		return availableStock;
	}


	/**
	 * This method asks the User for descriptions for a toy, specifically asking the
	 * Serial Number (if it is valid), and the general attributes of a toy, and if
	 * the serial number's first digit is a specific number, asks for specific
	 * attributes to pertains to the specific category.
	 * 
	 * @throws IOException- checks if a file does not exist at the promised location
	 */
	public void userAddToy(String userToyCategory, String userToyName, String userToyBrand, double userToyPrice, int userAgeAppropriate, int userAvailableCount, char userClassification,String serialNumInput) throws IOException {
		Figure Figure = new Figure(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
				userAvailableCount, Character.toUpperCase(userClassification), serialNumInput);
		toyList.add(Figure);
	}
	public void userAddToy(String userToyCategory, String userToyName, String userToyBrand, double userToyPrice, int userAgeAppropriate, int userAvailableCount, String serialNumInput, char userToySize, String userToyMaterial){
		Animals Animals = new Animals(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
				userAvailableCount, serialNumInput, userToySize, userToyMaterial);
		toyList.add(Animals);
	}
	public void userAddToy(String userToyCategory, String userToyName, String userToyBrand, double userToyPrice, int userAgeAppropriate, int userAvailableCount,String serialNumInput, String userPuzzleType){
		Puzzles Puzzle = new Puzzles(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
				userAvailableCount, userPuzzleType, serialNumInput);
		toyList.add(Puzzle);

	}
	public void userAddToy(String userToyCategory, String userToyName, String userToyBrand, double userToyPrice, int userAgeAppropriate, int userAvailableCount, String serialNumInput, int userToyMin, int userToyMax, String userToyDesigners){
		BoardGames BoardGames = new BoardGames(userToyCategory, userToyName, userToyBrand, userToyPrice,
				userAgeAppropriate, userAvailableCount, userToyDesigners, userToyMin, userToyMax, serialNumInput);
		toyList.add(BoardGames);
	}

	/**
	 * This method removes a toy, by prompting a Serial Number, then checks the
	 * whole ArrayList and gets the index of the prompted number and removes the toy
	 * from the list.
	 * 
	 * @return userRemoveToyInputChar User input if they want to remove the selected
	 *         Toy
	 * @throws IOException checks if the file exists at a given location
	 */
	public void userRemoveToy(Toys foundToy) throws IOException {
		for (Toys toy : toyList) {
			if (foundToy.getSerialNumber().equals(toy.getSerialNumber())) {
				toyList.remove(toy);
			}
		}
	}



	/**
	 * This method searches for a Toy through a prompted message, if the toy is in
	 * the array, return the toy
	 * 
	 * @return ArrayList This list holds 1 serial number that is identical to User
	 *         input.
	 * @throws IOException Checks if a file is found at a specific destination
	 */
	public ArrayList<Toys> findToySerialNumber(String toySerialNumber) throws IOException {
		ArrayList<Toys> identicalSerialNumber = new ArrayList<>();
		boolean isFound = false;

		for (Toys toy : toyList) {
			if (toySerialNumber.equals(toy.getSerialNumber())) {
				identicalSerialNumber.add(toy);
				isFound = true;
			}

		}

		return identicalSerialNumber;
	}

	/**
	 * This method prompts the user with 4 choices to list all toys with the same
	 * category, returns all the identical category toys
	 * 
	 * @return ArrayList this holds all the toys that has the same category
	 */
	public ArrayList<Toys> findIdenticalType(char userInputTypeChar) {
		ArrayList<Toys> identicalTypes = new ArrayList<>();
		switch (userInputTypeChar) {
			case '1':
				for (Toys toy : toyList) {
					if (toy instanceof Figure) {
						identicalTypes.add(toy);
					}
				}
				break;
			case '2':
				for (Toys toy : toyList) {
					if (toy instanceof Animals) {
						identicalTypes.add(toy);
					}
				}
				break;
			case '3':
				for (Toys toy : toyList) {
					if (toy instanceof Puzzles) {
						identicalTypes.add(toy);
					}
				}
				break;
			case '4':
				for (Toys toy : toyList) {
					if (toy instanceof BoardGames) {
						identicalTypes.add(toy);
					}
				}
				break;
		}
		return identicalTypes;
	}

	/**
	 * This method prompts the user with a toy name, if the toy name string is found
	 * within another string of a toy's add that toy to an arrayList and return it
	 * 
	 * @throws IOException: Checks if the file is found in the directed location
	 * @return findToyName returns all the toys that contains UserInput
	 */
	public ArrayList<Toys> findToyName(String userToyName) throws IOException {
		ArrayList<Toys> containsToyName = new ArrayList<>();
		boolean toyFound = false;

		for (Toys toy : toyList) {
			if ((toy.getName().toLowerCase()).contains(userToyName)) { // Use of knowledge of contains
																		// https://www.w3schools.com/java/ref_string_contains.asp
				containsToyName.add(toy);
				toyFound = true;
			}
		}
		if (toyFound == false) {
			Toys notFound = new Figure("","","", 0.00F,0,0,' '," ");
			containsToyName.add(notFound);
		}

		return containsToyName;
	}

	/**
	 * This method prints an ending message, and in the file prints each toy into
	 * the arraylist with the new changes the user might have left
	 * 
	 * @throws IOException: Checks if the file is found in the directed location
	 */
	public void exit() throws IOException {
		M.saveAndExitMenu();
		saving();
		System.exit(0);
	}

	/**
	 * This method goes through each toy, and depending on it's first digit in the
	 * Serial Number, would print the category specific toy description including
	 * its unique properties. Separated by semi-colons.
	 * 
	 * @throws IOException: Checks if the file is found in the directed location
	 */
	public void saving() throws IOException {

		PrintWriter fileWriting = new PrintWriter("res/toys.txt");
		for (Toys toy : toyList) {

			String serialNumber = toy.getSerialNumber();
			char digit = serialNumber.charAt(0);

			switch (digit) {
			case '0':
			case '1':
				fileWriting.println(toy.getSerialNumber() + ";" + toy.getName() + ";" + toy.getBrand() + ";"
						+ toy.getPrice() + ";" + toy.getAvailableCount() + ";" + toy.getAgeAppropriate() + ";"
						+ ((Figure) toy).getClassification());
				break;

			case '2':
			case '3':
				fileWriting.println(toy.getSerialNumber() + ";" + toy.getName() + ";" + toy.getBrand() + ";"
						+ toy.getPrice() + ";" + toy.getAvailableCount() + ";" + toy.getAgeAppropriate() + ";"
						+ ((Animals) toy).getMaterial() + ";" + ((Animals) toy).getSize());
				break;

			case '4':
			case '5':
			case '6':
				fileWriting.println(toy.getSerialNumber() + ";" + toy.getName() + ";" + toy.getBrand() + ";"
						+ toy.getPrice() + ";" + toy.getAvailableCount() + ";" + toy.getAgeAppropriate() + ";"
						+ ((Puzzles) toy).getPuzzleType());
				break;

			case '7':
			case '8':
			case '9':
				fileWriting.println(toy.getSerialNumber() + ";" + toy.getName() + ";" + toy.getBrand() + ";"
						+ toy.getPrice() + ";" + toy.getAvailableCount() + ";" + toy.getAgeAppropriate() + ";"
						+ ((BoardGames) toy).getMin() + "-" + ((BoardGames) toy).getMax() + ";"
						+ ((BoardGames) toy).getDesigners());
				break;

			}
		}
		fileWriting.close();
	}

}
