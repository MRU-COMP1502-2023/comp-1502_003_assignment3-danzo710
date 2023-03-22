package model.test.Puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Puzzles;

class PuzzlesTest {
	Puzzles testToy;

	@BeforeEach
	void test() {
		testToy = new Puzzles("Category", "George", "Toyota", (float) 19.00, 5, 9, "PuzzleType", "12835360879");
	}

	@Test
	void getPuzzleType() {
		testToy.setPuzzleType("Cheese");
		String result = testToy.getPuzzleType();
		assertEquals("Cheese", result);
	}

	@Test
	void test_toString() {
		String result = testToy.toString();
		assertEquals("Category: Puzzle, Name: " + "George" + ", Made by: " + "Toyota" + ", Price: " + "19.0" + "$, "
				+ "Number available: " + "9" + ", Recommended age: " + "5" + ", Type of Puzzle: " + "PuzzleType"
				+ ", Serial Number: " + "12835360879", result);
	}

}
