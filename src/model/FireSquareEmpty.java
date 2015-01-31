package model;

import java.util.List;

public class FireSquareEmpty extends FireSquare{

	public FireSquareEmpty(List<FireSquare> neighbors) {
		super(neighbors);
	}

	@Override
	public FireSquare chechStatus() {
		return this;
	}

	@Override
	public FireSquare checkNeighbor() {
		return null;
	}

	
}
