package view;

import controller.StoreManager;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static controller.StoreManager.toyList;

public class Menus {
	Scanner keyboard = new Scanner(System.in);
	StoreManager sm;

	public Menus(StoreManager sm){
		this.sm = sm;
	}
	private boolean first = false;


	/**
	 *This menu prints out all Selections, The user would have to provide an input to select the selected Menu.
	 * @return introMenuOption The user input of their selection of the menus.
	 */
	public char introMenu() {
		String introMenuOption = "";
		char option = 0;

			System.out.println("*****************************************************");
			System.out.println("*        WELCOME TO TOY STORE COMPANY!              *");
			System.out.println("*****************************************************");
			System.out.println();
			System.out.println("How May We Help You?");
			System.out.println();
			System.out.println("(1) Search Inventory and Purchase Toy");
			System.out.println("(2) Add New Toy");
			System.out.println("(3) Remove Toy");
			System.out.println("(4) Make a Gift Suggestion");
			System.out.println("(5) Save and Exit");
			System.out.println();
			System.out.print("Enter Option: ");
			introMenuOption = keyboard.nextLine();
			option = introMenuOption.charAt(0);
			return option;
//			if (Character.isLetter(option)) {
//				System.out.println("This is Not an Integer Number! Try again.");
//			} else if (introMenuOption != "1" || introMenuOption != "2" || introMenuOption != "3"
//					|| introMenuOption != "4") {
//				System.out.println("This is not a valid option! Try again.");

	}
	void validateOption(char option){
		if(Character.isLetter(option)){
			System.out.println("This is Not an Integer Number! Try again.");
		}
		if(option != 1){

		}
	}

	/**
	 *This method displays the Search Method, prompts user for input and uses that input displays the specific methods
	 * relating to the user input.
	 * @return searchInventoryMenuOption This is the userInput used to display specific menus
	 */
	public char searchInventoryMenu() {
		String searchInventoryMenuOption = null;
		char option = ' ';
		boolean flag = false;

		while (!flag) {
			System.out.println("Find Toys With:");
			System.out.println("(1) Serial Number (SN)");
			System.out.println("(2) Toy Name");
			System.out.println("(3) Type");
			System.out.println("(4) Back to Main Menu");

			System.out.print("Enter Option: ");
			searchInventoryMenuOption = keyboard.nextLine();
			option = searchInventoryMenuOption.charAt(0);

			if (Character.isLetter(option)) {
				System.out.println("This is Not an Integer Number! Try again.");
			}
			else if (option == '1' || option == '2'
					|| option == '3' || option == '4') {
				flag = true;
			}
			else{
				System.out.println("This is not a valid option! Try again.");
			}

		}
	return option;
	}

	/**
	 *
	 * @param toyList This holds an arraylist that holds an amount of toys, that relates to the user input, being,
	 * if they want an Identical Serial Number, if their input contains with other toy names, or if it is the
	 *                same Category.
	 * @return the specific toy that the user wants to purchase
	 * @throws IOException checks at a location, and looks if the file does exist
	 */
	  public Toys printInventory(ArrayList<Toys> toyList) throws IOException {
		  int counter = 0;
		  System.out.println();


		  System.out.print("Here are the search results:");
		  System.out.println();
		  for (Toys toy : toyList) {
			  counter++;
			  System.out.println("(" + counter + ")" + toy);
		  }
		  counter++;
		  System.out.println("(" + counter + ")" + "Back to Main Menu");
		  boolean flag = false;
		  String stringOption;
		  int option = 0;
		  while (!flag) {

			  System.out.print("Enter an option to purchase: ");
			  stringOption = keyboard.nextLine();
			  option = Integer.parseInt(stringOption);

			  if (option < counter && option >= 0) {
				  flag = true;
			  } else if (option == counter) {

				  start();
			  } else {
				  System.out.println("This is not a valid option! Try again.");
			  }
		  }
		  int index = option-1;
		  int indexCounter = 0;
		  Toys toyPurchase = null;
		  for(Toys toy: toyList){
			  if(index == indexCounter){
				  toyPurchase = toy;
				  break;
			  }
			  indexCounter++;
		  }
		  return toyPurchase;
	  }

	/**
	 * This method prints a message, and continues User back to the starting screen.
	 * @throws IOException looks at a location, and checks if the file exists.
	 */
	public void purchaseMenu() throws IOException {
		System.out.println("The Transaction Successfully Terminated!");

		start();
	}

	/**
	 * This method displays a message, if the user input is an enter, continues user to
	 * the next method.
	 */
	public void isEnterPressed(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press Enter to continue...");
		String input = keyboard.nextLine();
		while(input.length() != 0){
			input = keyboard.nextLine();
		}
	}
	public String showMenuFindIdenticalType() {
		System.out.println("Enter (1) Figures");
		System.out.println("Enter (2) Animals");
		System.out.println("Enter (3) Puzzles");
		System.out.println("Enter (4) Board Game");
		String userInput = keyboard.nextLine();
		String optionChosen = "";
		boolean flag = true;
		if(userInput.equals("")){
			flag = false;
		}
		if (flag){
			char userInputChar = userInput.charAt(0);

			switch (Character.toUpperCase(userInputChar)) {
				case '1':
					optionChosen = "Figure";
					break;
				case '2':
					optionChosen = "Animal";
					break;
				case '3':
					optionChosen = "Puzzle";
					break;
				case '4':
					optionChosen = "Board Game";
					break;
			}
		}
		return optionChosen;
	}

	public void removeToyMenu(char userRemoteToyInputChar) throws IOException {
		boolean rightInput = false;
		char userRemoveToyInputChar = ' ';
		if(userRemoteToyInputChar == 'y'){
			System.out.println("Item Removed!");
		}
		if(userRemoteToyInputChar == 'n'){
			System.out.println("Back to main menu");
		}
		System.out.println();

		start();
	}

	public void saveAndExitMenu() {
		// SAVE DATA make set methods

		System.out.println("Saving Data Into Database...");
		System.out.println("*********** THANKS FOR VISITING US! ***********");
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
			toySerialNumber = keyboard.nextLine();

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
	 * This method initiates the program First calls the start menu, and recieves a
	 * char, the char is then returned to be used to navigate other methods. Being,
	 * Searching for a toy, remvoing a toy, adding a toy, or terminating the program
	 *
	 * @throws IOException checks if the file is found at a specific location
	 */
	public void start() throws IOException {
		if(first)
		{
			isEnterPressed();
		}
		first = true;
		char introMenuOption = introMenu();
		char optionPrint = ' ';


		switch (introMenuOption) {
			case '1':
				char findToyOption = searchInventoryMenu();
				switch (findToyOption) {

					case '1':
						String userSerialNumber = serialNumberValidation();
						ArrayList<Toys> identicalSerialNumberList = sm.findToySerialNumber(userSerialNumber);
						Toys wantedToySerialNumber = printInventory(identicalSerialNumberList);
						if(!sm.purchase(wantedToySerialNumber)){
							System.out.println("Toy is not in Stock");
							start();
						}else{
							purchaseMenu();
						}

						break;
					case '2':
						String nameSearch = nameSearchView();
						ArrayList<Toys> containsToyName = sm.findToyName(nameSearch);
						if(!containsToyName.get(0).getCategory().equals("")) {
							Toys wantedToy = printInventory(containsToyName);
							if(!sm.purchase(wantedToy)){
								System.out.println("Toy is not in Stock");
								start();
							}else{
								purchaseMenu();
							}
						}else{
							System.out.println("Cannot Find Toy... Directing back to Menu");
							start();
						}

						break;
					case '3':
						char userInput = identicalTypesView();
						ArrayList<Toys> identicalTypeList = sm.findIdenticalType(userInput);
						Toys wantedToy = printInventory(identicalTypeList);
						if(!sm.purchase(wantedToy)){
							System.out.println("Toy is not in Stock");
							start();
						}else{
							purchaseMenu();
						}
						break;
					case '4':

						start();
						break;
					default:
						System.out.println("This is not a valid option! Try again.");
				}
				break;
			case '2':
				addingToy();
				break;
			case '3':
				removeToyView();
				break;
			case '4':
				ArrayList<Toys> giftList = giftSuggestion();
				Toys wantedToy = printInventory(giftList);
				if(!sm.purchase(wantedToy)){
					System.out.println("Toy is not in Stock");
					start();
				}else{
					purchaseMenu();
				}
				break;
			case'5':
				sm.exit();
				break;
			default:
				System.out.println("This is not a valid option! Try again.");
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
			addingToy();
		}
		// for loop repeats for each character in the serial number
		// checks characters if they have letters user must input a new serial number
		for (int i = 0; i < serialNum.length(); i++) {
			char serialNumChars = serialNum.charAt(i);
			if (Character.isLetter(serialNumChars)) {
				System.out.println("Serial numbers must be only have integers");
				System.out.println();
				addingToy();
			}

		}

		for (Toys toy : toyList) {
			if (serialNum.equals(toy.getSerialNumber())) {
				System.out.println("That serial Number already exists try again");
				System.out.println();
				addingToy();
			}
		}

	}
	public void addingToy() throws IOException {

		Scanner keyboard = new Scanner(System.in);
		String userToyCategory;

		// Taking user input for common attributes
		System.out.print("Enter Serial Number: ");
		String serialNumInput = keyboard.nextLine();
		// Checks if the serial number is valid
		isSerialNumValid(serialNumInput);
		System.out.println();

		System.out.print("Enter Toy Name: ");
		String userToyName = keyboard.nextLine();
		System.out.println();

		System.out.print("Enter Toy Brand: ");
		String userToyBrand = keyboard.nextLine();
		System.out.println();

		System.out.print("Enter Toy Price: ");
		String userInputPrice = keyboard.nextLine();
		double userToyPrice = Double.parseDouble(userInputPrice);
		// Exception for if the price is a negative number stops the program
		if (userToyPrice < 0) {
			throw new IllegalArgumentException("Price must be a positive double");
		}
		System.out.println();

		System.out.print("Enter Available Counts: ");
		String userInputAvailableCount = keyboard.nextLine();
		int userAvailableCount = Integer.parseInt(userInputAvailableCount);
		System.out.println();

		System.out.print("Enter Appropriate Age: ");
		String userInputAgeAppropriate = keyboard.nextLine();
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
				String userClassificationInput = keyboard.nextLine();
				char userClassification = userClassificationInput.charAt(0);
				System.out.println();

				sm.userAddToy(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
						userAvailableCount, Character.toUpperCase(userClassification), serialNumInput);

				System.out.println("New Toy Added!");

				start();
				break;

			case '2':
			case '3':
				userToyCategory = "Animal";
				System.out.print("Enter Material: ");
				String userToyMaterial = keyboard.nextLine();
				System.out.println();

				System.out.print("Enter the size: ");
				String sizeInput = keyboard.nextLine();
				char userToySize = Character.toUpperCase(sizeInput.charAt(0));
				System.out.println();

				Animals Animals = new Animals(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
						userAvailableCount, serialNumInput, userToySize, userToyMaterial);
				toyList.add(Animals);
				sm.userAddToy(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
						userAvailableCount, serialNumInput, userToySize, userToyMaterial);
				System.out.println("New Toy Added!");
				start();

				break;

			case '4':
			case '5':
			case '6':
				userToyCategory = "Puzzle";
				System.out.print("Enter puzzle type: ");
				String userPuzzleType = keyboard.nextLine();
				System.out.println();

				sm.userAddToy(userToyCategory, userToyName, userToyBrand, userToyPrice, userAgeAppropriate,
						userAvailableCount, userPuzzleType, serialNumInput);
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
				String userToyDesigners = keyboard.nextLine();
				System.out.println();

				BoardGames BoardGames = new BoardGames(userToyCategory, userToyName, userToyBrand, userToyPrice,
						userAgeAppropriate, userAvailableCount, userToyDesigners, userToyMin, userToyMax, serialNumInput);
				toyList.add(BoardGames);
				sm.userAddToy(userToyCategory, userToyName, userToyBrand, userToyPrice,
						userAgeAppropriate, userAvailableCount, userToyDesigners, userToyMin, userToyMax, serialNumInput);
				System.out.println("New Toy Added!");

				start();
				break;
		}
	}
	public ArrayList<Toys> giftSuggestion() throws IOException {
		System.out.println("What is the age?");
		String giftAge = keyboard.nextLine();
		System.out.println("What is the Category?");
		String option = showMenuFindIdenticalType();
		System.out.println("What is the Price Range?(separate the minimum and maximum with a - no $ needed)");
		String giftPriceRange = keyboard.nextLine();
		return  sm.searchGiftOptions(giftAge, option, giftPriceRange);

	}
	public void removeToyView() throws IOException {
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
		System.out.println("This Item Found: ");
		System.out.println(foundToy);
		while (wrongInput) {

			System.out.print("Do you want to remove it? (Y/N)?");
			userRemoveToyInput = keyboard.nextLine();
			userRemoveToyInputChar = userRemoveToyInput.toLowerCase().charAt(0);

			if (userRemoveToyInputChar == 'y' || userRemoveToyInputChar == 'n') {
				wrongInput = false;
			} else {
				System.out.println("Wrong input... Try again");
			}
		}
		if (userRemoveToyInputChar == 'y') {
			if (foundToy.getAvailableCount() == 0) {
				System.out.println("Serial Number cannot be found");
				start();
			}
			sm.userRemoveToy(foundToy);
			removeToyMenu(userRemoveToyInputChar);
			}
		else if (userRemoveToyInputChar == 'n') {
			start();
		}
	}
	public char identicalTypesView() {
		boolean notInstanceOf = false;
		String userInputType = null;
		char userInputTypeChar = 0;

		while (!notInstanceOf) {
			System.out.println("Enter (1) Figures");
			System.out.println("Enter (2) Animals");
			System.out.println("Enter (3) Puzzles");
			System.out.println("Enter (4) Board Game");

			userInputType = keyboard.nextLine();
			userInputTypeChar = userInputType.charAt(0);
			switch (userInputTypeChar) {
				case '1':
				case '2':
				case '3':
				case '4':
					notInstanceOf = true;
				default:
					System.out.println("This is not a valid option! Try again.");
			}
		}
		return userInputTypeChar;
	}
	public String nameSearchView(){

		System.out.print("Enter Toy Name: ");
		String userInput = keyboard.nextLine();
		userInput = userInput.toLowerCase();

		return userInput;
	}
}
