package com.example.junk;

public class ElseIfDirectionUtil implements IDirectionUtil {

	@Override
	public Directions getDirection(int xPoint, int yPoint) {
		Directions direction = null;
		if (xPoint > 0 && yPoint > 0) {
			direction = Directions.SouthEast;
		}
		else if (xPoint > 0 && yPoint < 0) {
			direction = Directions.NorthEast;
		}
		else if (xPoint < 0 && yPoint > 0) {
			direction = Directions.SouthWest;
		}
		else if (xPoint < 0 && yPoint < 0) {
			direction = Directions.NorthWest;
		}
		else if (xPoint == 0 && yPoint < 0) {
			direction = Directions.North;
		}
		else if (xPoint == 0 && yPoint > 0) {
			direction = Directions.South;
		}
		else if (xPoint > 0 && yPoint == 0) {
			direction = Directions.East;
		}
		else if (xPoint < 0 && yPoint == 0) {
			direction = Directions.West;
		}
		return direction;
	}

}
