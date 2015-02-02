package model;

import java.util.List;

import javafx.scene.paint.Color;

public class LifeSquareDead extends LifeSquare{

	private Color myColor = Color.WHITE;
	
	public LifeSquareDead(List<LifeSquare> neighbors) {
		super(neighbors);
	}

	public LifeSquareDead() {
	}

	@Override
	protected int returnCount() {
		return 0;
	}

	@Override
	protected LifeSquare checkStatus(int alive) {
		if(alive == 3){
			return new LifeSquareAlive(myNeighbors);
		}
		else return this;
	}

	@Override
	protected Color getColor() {
		return myColor;
	}

}
