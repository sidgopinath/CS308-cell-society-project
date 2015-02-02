package model;

import java.util.List;

public class LifeSquareAlive extends LifeSquare{

	public LifeSquareAlive(List<LifeSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
	}

	public LifeSquareAlive(){
	}
	
	@Override
	protected int returnCount() {
		return 1;
	}

	@Override
	protected LifeSquare checkStatus(int alive) {
		if(alive == 2 || alive == 3){
			return this;
		}
		else return new LifeSquareDead(myNeighbors);
		
	}

	

}
