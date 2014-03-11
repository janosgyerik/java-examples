package com.example.junk;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionUtilTest {

	IDirectionUtil directionUtil = new BinaryDirectionUtil();

	@Test
	public void testNorthEast() {
		assertEquals(IDirectionUtil.Directions.NorthEast,
				directionUtil.getDirection(1, -1));
	}

	@Test
	public void testNorthWest() {
		assertEquals(IDirectionUtil.Directions.NorthWest,
				directionUtil.getDirection(-1, -1));
	}

	@Test
	public void testSouthEast() {
		assertEquals(IDirectionUtil.Directions.SouthEast,
				directionUtil.getDirection(1, 1));
	}

	@Test
	public void testSouthWest() {
		assertEquals(IDirectionUtil.Directions.SouthWest,
				directionUtil.getDirection(-1, 1));
	}

	@Test
	public void testNorth() {
		assertEquals(IDirectionUtil.Directions.North,
				directionUtil.getDirection(0, -1));
	}

	@Test
	public void testEast() {
		assertEquals(IDirectionUtil.Directions.East,
				directionUtil.getDirection(1, 0));
	}

	@Test
	public void testSouth() {
		assertEquals(IDirectionUtil.Directions.South,
				directionUtil.getDirection(0, 1));
	}

	@Test
	public void testWest() {
		assertEquals(IDirectionUtil.Directions.West,
				directionUtil.getDirection(-1, 0));
	}

}
