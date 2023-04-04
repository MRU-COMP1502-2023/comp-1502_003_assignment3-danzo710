package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import controller.StoreManager;
import view.Menus;

/**
 * App driver main class all code starts here
 * makes store manager and calls the method for loading the file and the start method
 */
public class AppDriver {
	/**
	 * The starter of the program.
	 * @param args userInput from the cmd.
	 * @throws IOException validates if a file is found at a specific location.
	 */
	public static void main(String[] args) throws IOException {
		StoreManager sm = new StoreManager();
		Menus menu = new Menus(sm);
		sm.loadFile();
		menu.start();
	}
}
