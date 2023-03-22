package model.test.figures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Figure;

class FiguresTest {
	Figure testToy;

	@BeforeEach
	void innit() {
		testToy = new Figure("Category", "George", "Toyota", (float) 19.00, 5, 9, 'A', "12835360879");
	}

	@Test
	void testClassification() {
		testToy.setClassification('D');
		char result = testToy.getClassification();
		assertEquals('D', result);
	}

	@Test
	void test_toString() {
		String result = testToy.toString();
		assertEquals("Category: Figure, Name: " + "George" + ", Made by: " + "Toyota" + " Price: " + 19.0 + "$, "
				+ "Number available: " + 9 + ", Recommended age: " + 5 + ", figure Classification: " + 'A'
				+ ", Serial Number: " + "12835360879", result);
	}

}
