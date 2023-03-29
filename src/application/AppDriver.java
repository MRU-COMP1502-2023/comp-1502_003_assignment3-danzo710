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
	public static void main(String[] args) throws IOException {
		StoreManager sm = new StoreManager();
		Menus menu = new Menus(sm);
		sm.loadFile();
		menu.start();
	}
}
