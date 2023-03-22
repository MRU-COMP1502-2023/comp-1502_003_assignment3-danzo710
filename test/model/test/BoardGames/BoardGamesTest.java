package model.test.BoardGames;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.BoardGames;

class BoardGamesTest {
	BoardGames testToy;

	@BeforeEach
	public void init() {
		testToy = new BoardGames("Animal", "George", "Toyota", (float) 19.00, 5, 9, "Designers", 1, 2, "12835360879");
	}

	@Test
	void testGetDesigners() {
		testToy.setDesigners("Prince");
		String designers = testToy.getDesigners();
		assertEquals("Prince", designers);
	}

	@Test
	void test_toString() {
		String result = testToy.toString();
		assertEquals("Category: Board Game, Name: " + "George" + ", Made by: " + "Toyota" + " Price: " + 19.0 + "$, "
				+ "Number available: " + 9 + ", Recommended age: " + 5 + ", Game Designers: " + "Designers"
				+ ", Number of Players: " + 1 + "-" + 2 + ", Serial Number: " + "12835360879", result);
	}

}
