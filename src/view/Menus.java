package view;

import controller.StoreManager;
import model.Toys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
	Scanner keyboard = new Scanner(System.in);
	StoreManager sm;

	public Menus(StoreManager sm){
		this.sm = sm;
	}


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
			System.out.println("(4) Save and Exit");
			System.out.println();
			System.out.print("Enter Option: ");
			introMenuOption = keyboard.next();
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
			searchInventoryMenuOption = keyboard.next();
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
		  int option = 0;
		  while (!flag) {

			  System.out.print("Enter an option to purchase: ");
			  option = keyboard.nextInt();

			  if (option < counter && option >= 0) {
				  flag = true;
			  } else if (option == counter) {

				  sm.start();
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

		sm.start();
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

		sm.start();
	}

	public void saveAndExitMenu() {
		// SAVE DATA make set methods

		System.out.println("Saving Data Into Database...");
		System.out.println("*********** THANKS FOR VISITING US! ***********");
	}
}
