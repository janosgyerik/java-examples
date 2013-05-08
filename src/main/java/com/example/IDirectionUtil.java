package com.example;

public interface IDirectionUtil {
	
	enum Directions {
		SouthEast,
		NorthEast,
		SouthWest,
		NorthWest,
		North,
		South,
		East,
		West, 
		None,
	}
	
	public Directions getDirection(int xPoint, int yPoint);

}
