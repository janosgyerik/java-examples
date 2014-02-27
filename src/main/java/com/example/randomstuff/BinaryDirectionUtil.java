package com.example.randomstuff;

public class BinaryDirectionUtil implements IDirectionUtil {

	@Override
	public Directions getDirection(int xPoint, int yPoint) {
		int num = 8 * (xPoint == 0 ? 0 : xPoint > 0 ? 1 : 2);
		num += yPoint == 0 ? 0 : yPoint > 0 ? 1 : 2;
		switch (num) {
		case 01:
			return Directions.South;
		case 02:
			return Directions.North;
		case 010:
			return Directions.East;
		case 011:
			return Directions.SouthEast;
		case 012:
			return Directions.NorthEast;
		case 020:
			return Directions.West;
		case 021:
			return Directions.SouthWest;
		case 022:
			return Directions.NorthWest;
		}
		return Directions.None;
	}

}
