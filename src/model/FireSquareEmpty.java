package model;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is empty for the fire sim.
 * @author Sunjeev
 *
 */

public class FireSquareEmpty extends FireSquare{

	public FireSquareEmpty(List<FireSquare> neighbors) {
		super(neighbors);
		myColor = Color.YELLOW;
	}

	public FireSquareEmpty(){
		myColor = Color.YELLOW;
	}
	@Override
	public FireSquare chechStatus() {
		return this;
	}

	@Override
	public FireSquare checkNeighbor() {
		return null;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	
}
