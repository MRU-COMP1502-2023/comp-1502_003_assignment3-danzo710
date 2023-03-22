package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	ArrayList<Toys> toyList = new ArrayList<Toys>();
	File toyFile = new File(FILE_PATH);
	Scanner keyboard = new Scanner(System.in);
	private char classification;
	private boolean first = false;
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
			float price = Float.parseFloat(values[3]);
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

	/**
	 * This method initiates the program First calls the start menu, and recieves a
	 * char, the char is then returned to be used to navigate other methods. Being,
	 * Searching for a toy, remvoing a toy, adding a toy, or terminating the program
	 * 
	 * @throws IOException checks if the file is found at a specific location
	 */
	public void start() throws IOException {
		if(first)
		{
			M.isEnterPressed();
		}
		first = true;
		char introMenuOption = M.introMenu();
		char optionPrint = ' ';


		switch (introMenuOption) {
		case '1':
			char findToyOption = M.searchInventoryMenu();
			switch (findToyOption) {

			case '1':
				ArrayList<Toys> identicalSerialNumberList = findToySerialNumber();
				Toys wantedToySerialNumber = M.printInventory(identicalSerialNumberList);
				purchase(wantedToySerialNumber);
				M.purchaseMenu();
				break;
			case '2':
				ArrayList<Toys> containsToyName = findToyName();
				M.printInventory(containsToyName);
				break;
			case '3':
				ArrayList<Toys> identicalTypeList = findIdenticalType();
				Toys wantedToy = M.printInventory(identicalTypeList);
				purchase(wantedToy);
				M.purchaseMenu();
				break;
			case '4':

				start();
				break;
			default:
				System.out.println("This is not a valid option! Try again.");
			}
			break;
		case '2':
			userAddToy();
			break;
		case '3':
			char inputResponse = userRemoveToy(); // 0298753495
			break;
		case '4':
			exit();
			break;
		default:
			System.out.println("This is not a valid option! Try again.");
			start();
		}
	}

	/**
	 * This method checks if the available count is zero, if it is zero remove from
	 * the list. If the available count is greater than zero, this method minuses
	 * one from the toal available count.
	 * 
	 * @param wantedToy this is a toy that the user wants to purchase
	 * @throws IOException In case File is not found
	 */
	private void purchase(Toys wantedToy) throws IOException {
		boolean notEqual = true;

		for (Toys toy : toyList) {

			if (wantedToy.getAvailableCount() == 0) {
				toyList.remove(wantedToy);
				break;
			}

			if (wantedToy.equals(toy)) {
				toy.setAvailableCount(toy.getAvailableCount() - 1);
				wantedToy = toy;
				notEqual = false;
				break;
			}

		}
		if (notEqual == true) {
			System.out.println("Toy is not in Stock");
			start();
		}
	}

	/**
	 * This method validates if the prompted Serial Number is a valid message, by
	 * checking if the Serial Number has 10 digits, has only integers, and if the
	 * Serial Number already exists in the toyList array
	 * 
	 * @param serialNum: The prompted serial number from the user
	 * @throws IOException: checks if the file does not exist at a specific location
	 */
	public void isSerialNumValid(String serialNum) throws IOException {

		if (serialNum.length() != 10) {
			System.out.println("Serial Number MUSt be 10 digits try again");
			System.out.println();
			userAddToy();
		}
		// for loop repeats for each character in the serial number
		// checks characters if they have letters user must input a new serial number
		for (int i = 0; i < serialNum.length(); i++) {
			char serialNumChars = serialNum.charAt(i);
			if (Character.isLetter(serialNumChars)) {
				System.out.println("Serial numbers must be only have integers");
				System.out.println();
				userAddToy();
			}

		}

		for (Toys toy : toyList) {
			if (serialNum.equals(toy.getSerialNumber())) {
				System.out.println("That serial Number already exists try again");
				System.out.println();
				userAddToy();
			}
		}

	}

	/**
	 * This method asks the User for descriptions for a toy, specifically asking the
	 * Serial Number (if it is valid), and the general attributes of a toy, and if
	 * the serial number's first digit is a specific number, asks for specific
	 * attributes to pertains to the specific category.
	 * 
	 * @throws IOException- checks if a file does not exist at the promised location
	 */
	public void userAddToy() throws IOException {
		Scanner keyboard = new Scanner(System.in);
		String userToyCategory;

		// Taking user input for common attributes
		System.out.print("Enter Serial Number: ");
		String serialNumInput = keyboard.next();
		// Checks if the serial number is valid
		isSerialNumValid(serialNumInput);
		System.out.println();

		System.out.print("Enter Toy Name: ");
		String userToyName = keyboard.next();
		System.out.println();

		System.out.print("Enter Toy Brand: ");
		String userToyBrand = keyboard.next();
		System.out.println();

		System.out.print("Enter Toy Price: ");
		String userInputPrice = keyboard.next();
		float userToyPrice = Float.parseFloat(userInputPrice);
		// Exception for if the price is a negative number stops the program
		if (userToyPrice < 0) {
			throw new IllegalArgumentException("Price must be a positive float");
		}
		System.out.println();

		System.out.print("Enter Available Counts: ");
		String userInputAvailableCount = keyboard.next();
		int userAvailableCount = Integer.parseInt(userInputAvailableCount);
		System.out.println();

		System.out.print("Enter Appropriate Age: ");
		String userInputAgeAppropriate = keyboard.next();
		int userAgeAppropriate = Integer.parseInt(userInputAgeAppropriate);
		System.out.println();

		char digit = serialNumInput.charAt(0);

		// Swtich for checking the first digit of serial number
		// Cases ask for the unique attributes of the corresponding digit
		// New toy is then created and added to the array list
		// User goes back to main menu
		switch (digit) {
		case '0':
		case '1':
			userToyCategory = "Figure";
			System.out.print("Enter figure classification(A,H,D): ");
			String userClassificationInput = keyboard.next();
			char userClassification = userClassificationInput.charAt(0);
			System.out.println();
			Figure Figure = new Figure(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
					userAvailableCount, Character.toUpperCase(userClassification), serialNumInput);
			toyList.add(Figure);
			System.out.println("New Toy Added!");

			start();
			break;

		case '2':
		case '3':
			userToyCategory = "Animal";
			System.out.print("Enter Material: ");
			String userToyMaterial = keyboard.next();
			System.out.println();

			System.out.print("Enter the size: ");
			String sizeInput = keyboard.next();
			char userToySize = Character.toUpperCase(sizeInput.charAt(0));
			System.out.println();

			Animals Animals = new Animals(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
					userAvailableCount, serialNumInput, userToySize, userToyMaterial);
			toyList.add(Animals);
			System.out.println("New Toy Added!");

			start();

			break;

		case '4':
		case '5':
		case '6':
			userToyCategory = "Puzzle";
			System.out.print("Enter puzzle type: ");
			String userPuzzleType = keyboard.next();
			System.out.println();

			Puzzles Puzzle = new Puzzles(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
					userAvailableCount, userPuzzleType, serialNumInput);
			toyList.add(Puzzle);
			System.out.println("New Toy Added!");

			start();
			break;

		case '7':
		case '8':
		case '9':
			userToyCategory = "Board Game";
			System.out.print("Enter minimum amount of players: ");
			int userToyMin = keyboard.nextInt();
			System.out.println();

			System.out.print("Enter maximum amount of players: ");
			int userToyMax = keyboard.nextInt();
			System.out.println();

			// Custom exception happens when the min the user inputted is greater than the
			// max they inputted
			if (userToyMin > userToyMax) {
				throw new IllegalArgumentException(
						"Minimum number of players must be less than the maximum number of players");
			}

			System.out.print("Enter Designer Names (Use ',' to separate the names if there is more than one name):  ");
			String userToyDesigners = keyboard.next();
			System.out.println();

			BoardGames BoardGames = new BoardGames(userToyCategory, userToyName, userToyBrand, userToyPrice,
					userAgeAppropriate, userAvailableCount, userToyDesigners, userToyMin, userToyMax, serialNumInput);
			toyList.add(BoardGames);
			System.out.println("New Toy Added!");

			start();
			break;
		}

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
	private char userRemoveToy() throws IOException {
		String serialNumberValid;
		Toys foundToy = null;
		boolean wrongInput = true;
		String userRemoveToyInput;
		char userRemoveToyInputChar = 0;

		serialNumberValid = serialNumberValidation();

		for (Toys toy : toyList) {
			if (serialNumberValid.equals(toy.getSerialNumber())) {
				foundToy = toy;
			}
		}

		while (wrongInput) {

			System.out.print("Do you want to remove it? (Y/N)?");
			userRemoveToyInput = keyboard.next();
			userRemoveToyInputChar = userRemoveToyInput.toLowerCase().charAt(0);

			if (userRemoveToyInputChar == 'y' || userRemoveToyInputChar == 'n') {
				wrongInput = false;
			} else {
				System.out.println("Wrong input... Try again");
			}
		}

		if (userRemoveToyInputChar == 'y') {
			for (Toys toy : toyList) {
				if (foundToy.getSerialNumber().equals(toy.getSerialNumber())) {
					toyList.remove(toy);
					M.removeToyMenu(userRemoveToyInputChar);
					break;
				} else if (foundToy.getAvailableCount() == 0) {
					System.out.println("Serial Number cannot be found");

					start();
				}
			}
		} else if (userRemoveToyInputChar == 'n') {
			start();
		}
		return userRemoveToyInputChar;
	}

	/**
	 * This method prompts the user for a Serial Number, checks if the number is 10
	 * digits long, has no letters, or if the item already exists in the ArrayList,
	 * similar to the first Validation, but returns the prompted String
	 * 
	 * @return toySerialNumber The user input for Serial Number
	 * @throws IOException checks if the file is found in a particular destination
	 */
	public String serialNumberValidation() throws IOException {
		String toySerialNumber = null;
		boolean validSerialNumber = false;
		boolean hasLetter = false;
		boolean inStore = false;

		while (!validSerialNumber) {
			System.out.print("Enter a Serial Number: ");
			toySerialNumber = keyboard.next();

			for (int i = 0; i < toySerialNumber.length(); i++) {
				char serialNumChars = toySerialNumber.charAt(i);
				if (Character.isLetter(serialNumChars)) {
					hasLetter = true;
				}
			}
			if (hasLetter) {
				System.out.println("Serial numbers must only have integers");
			}
			if (toySerialNumber.length() != 10) {
				System.out.println("Serial Number must have 10 digits try again");
			}
			for (Toys toy : toyList) {
				if (toySerialNumber.equals(toy.getSerialNumber())) {
					inStore = true;
				}
			}
			if (!hasLetter && toySerialNumber.length() == 10 && !inStore) {
				System.out.println("Item is not found in Store... Try Again");

				start();
			}
			if (!hasLetter && toySerialNumber.length() == 10 && inStore) {
				validSerialNumber = true;
			}
			hasLetter = false;
		}
		return toySerialNumber;
	}

	/**
	 * This method searches for a Toy through a prompted message, if the toy is in
	 * the array, return the toy
	 * 
	 * @return ArrayList This list holds 1 serial number that is identical to User
	 *         input.
	 * @throws IOException Checks if a file is found at a specific destination
	 */
	private ArrayList<Toys> findToySerialNumber() throws IOException {
		String toySerialNumber = null;
		toySerialNumber = serialNumberValidation();

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
	private ArrayList<Toys> findIdenticalType() {
		ArrayList<Toys> identicalTypes = new ArrayList<>();
		boolean notInstanceOf = false;
		String userInputType = null;
		String userInputTypeLowerCase = null;
		char userInputTypeChar;

		while (!notInstanceOf) {
			System.out.println("Enter (1) Figures");
			System.out.println("Enter (2) Animals");
			System.out.println("Enter (3) Puzzles");
			System.out.println("Enter (4) Board Game");

			userInputType = keyboard.next();
			userInputTypeChar = userInputType.charAt(0);
			switch (userInputTypeChar) {
			case '1':
				for (Toys toy : toyList) {
					if (toy instanceof Figure) {
						identicalTypes.add(toy);
					}
				}
				notInstanceOf = true;
				break;
			case '2':
				for (Toys toy : toyList) {
					if (toy instanceof Animals) {
						identicalTypes.add(toy);
					}
				}
				notInstanceOf = true;
				break;
			case '3':
				for (Toys toy : toyList) {
					if (toy instanceof Puzzles) {
						identicalTypes.add(toy);
					}
				}
				notInstanceOf = true;
				break;
			case '4':
				for (Toys toy : toyList) {
					if (toy instanceof BoardGames) {
						identicalTypes.add(toy);
					}
				}
				notInstanceOf = true;
				break;
			default:
				System.out.println("This is not a valid option! Try again.");
			}

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
	private ArrayList<Toys> findToyName() throws IOException {
		ArrayList<Toys> containsToyName = new ArrayList<>();
		boolean toyFound = false;

		System.out.print("Enter Toy Name: ");
		String userInput = keyboard.next();
		userInput = userInput.toLowerCase();

		for (Toys toy : toyList) {
			if ((toy.getName().toLowerCase()).contains(userInput)) { // Use of knowledge of contains
																		// https://www.w3schools.com/java/ref_string_contains.asp
				containsToyName.add(toy);
				toyFound = true;
			}
		}
		if (toyFound == false) {
			System.out.println("Cannot Find Toy... Directing back to Menu");
			start();
		}

		return containsToyName;
	}

	/**
	 * This method prints an ending message, and in the file prints each toy into
	 * the arraylist with the new changes the user might have left
	 * 
	 * @throws IOException: Checks if the file is found in the directed location
	 */
	private void exit() throws IOException {
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
