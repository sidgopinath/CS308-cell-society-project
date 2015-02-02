package model;

import java.util.List;

public class LifeSquareDead extends LifeSquare{

	public LifeSquareDead(List<LifeSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
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

}
