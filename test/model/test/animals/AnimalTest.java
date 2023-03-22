package model.test.animals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Animals;

class AnimalTest {

	Animals testToy;

	@BeforeEach
	public void init() {
		testToy = new Animals("Animal", "George", "Toyota", (float) 19.00, 5, 9, "12835360879", 's', "Leather");
	}

	@Test
	void test_toString() {
		String result = testToy.toString();
		assertEquals("Category: Animal, Name: " + "George" + ", Made by: " + "Toyota," + " Price: " + "19.0" + "$, "
				+ "Number available: " + "9" + ", Recommended age: " + "5" + ", Size: " + 's' + ", Material Used: "
				+ "Leather" + ", Serial Number: " + "12835360879", result);
	}

	@Test
	void test_setAndGetCategory() {
		testToy.setCategory("Testing");
		String result = testToy.getCategory();
		assertEquals("Testing", result);
	}

	@Test
	void test_getName() {
		testToy.setName("Lemon");
		String result = testToy.getName();
		assertEquals("Lemon", result);
	}

	@Test
	void test_getBrand() {
		testToy.setBrand("Maroon");
		String result = testToy.getBrand();
		assertEquals("Maroon", result);
	}

	@Test
	void test_getPrice() {
		testToy.setPrice(20);
		float result = testToy.getPrice();
		assertEquals(20, result);
	}

	@Test
	void test_getAvailableCount() {
		testToy.setAvailableCount(8);
		int result = testToy.getAvailableCount();
		assertEquals(8, result);
	}

	@Test
	void test_getSize() {
		testToy.setSize('l');
		char result = testToy.getSize();
		assertEquals('l', result);
	}

	@Test
	void test_getMaterial() {
		testToy.setMaterial("Plastic");
		String material = testToy.getMaterial();
		assertEquals("Plastic", material);
	}

}
