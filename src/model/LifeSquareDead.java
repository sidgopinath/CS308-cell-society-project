package model;

import java.util.List;

public class LifeSquareDead extends LifeSquare{

	public LifeSquareDead(List<LifeSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int returnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
