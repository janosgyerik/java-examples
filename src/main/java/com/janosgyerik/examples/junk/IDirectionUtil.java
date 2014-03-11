package com.janosgyerik.examples.junk;

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
