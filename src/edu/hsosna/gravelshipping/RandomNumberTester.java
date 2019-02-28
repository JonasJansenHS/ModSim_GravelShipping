package edu.hsosna.gravelshipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class RandomNumberTester {
	
	private static RandomNumberGenerator2<Integer> loadShipTime = new RandomNumberGenerator2<Integer>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		loadShipTime.add(new KeyValuePair<Integer>(0.3d, 10));
		loadShipTime.add(new KeyValuePair<Integer>(0.7d, 15));
		loadShipTime.add(new KeyValuePair<Integer>(1.0d, 17));
	}

	@Test
	void test() {
		assertEquals((int)loadShipTime.nextValue(0.2), 10);	
	}
	@Test
	void test2() {
		assertEquals((int)loadShipTime.nextValue(0.3), 10);
	}
	@Test
	void test3() {
		assertEquals((int)loadShipTime.nextValue(0.31), 15);
	}
}
