package com.revature.pms.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArithmeticCalculatorTest {

	ArithmeticCalculator calculator;
	int expected =0;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass called");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass called");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setUp called");
		expected = 20;
		calculator = new ArithmeticCalculator();
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown called");
		calculator = null;
	}

	@Test
   @DisplayName("Testing add method -1 ")
	void testAdd1() {
		System.out.println("testAdd1 called");
		int actual = calculator.add(10, 10);
		assertEquals(expected, actual);
	}
	
	@Test	
	@DisplayName("Testing add method -2 ")
	void testAdd2() {
		System.out.println("testAdd2 called");
		int actual = calculator.add(1, 19);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Testing Get message  ")
	void testGetMessage() {
		System.out.println("testGetMessage called");

		String expected = "Hello and welcome to REVATURE App";
		String actual = calculator.getMessage();
		assertEquals(expected, actual);
	}

}
