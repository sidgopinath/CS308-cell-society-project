package model;

import java.util.List;

import javafx.scene.paint.Color;

public class FireSquareEmpty extends FireSquare{

	public FireSquareEmpty(List<FireSquare> neighbors) {
		super(neighbors);
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
