package model;

import java.util.List;

public class LifeSquareAlive extends LifeSquare{

	public LifeSquareAlive(List<LifeSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int returnCount() {
		return 1;
	}

	

}
