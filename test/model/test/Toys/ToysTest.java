package model.test.Toys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Toys;

class ToysTest {
	Toys testToy;

	@BeforeEach
	void innit() {
		testToy = new Toys("Category", "George", "Toyota", (float) 19.00, 5, 9, "12835360879");
	}

	@Test
	void test_getCategory() {
		testToy.setCategory("BoardGame");
		String result = testToy.getCategory();
		assertEquals("BoardGame", result);
	}

	@Test
	void test_getName() {
		testToy.setName("Kevin");
		String result = testToy.getName();
		assertEquals("Kevin", result);
	}

	@Test
	void test_getBrand() {
		testToy.setBrand("Mitsubishi");
		String result = testToy.getBrand();
		assertEquals("Mitsubishi", result);
	}

	@Test
	void test_getPrice() {
		testToy.setPrice(11);
		float result = testToy.getPrice();
		assertEquals(11, result);
	}

	@Test
	void test_getAgeAppropriate() {
		testToy.setAgeAppropriate(6);
		int result = testToy.getAgeAppropriate();
		assertEquals(6, result);
	}

	@Test
	void test_getSerialNumber() {
		testToy.setSerialNumber("12835360878");
		String result = testToy.getSerialNumber();
		assertEquals("12835360878", result);
	}

}
