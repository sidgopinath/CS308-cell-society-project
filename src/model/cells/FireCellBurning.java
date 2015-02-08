package model.cells;

import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is on fire for the fire sim.
 * @author Sunjeev
 *
 */

public class FireCellBurning extends FireCell{

	private int myProbCatch;
	private Random myRandom;
	
	public FireCellBurning(List<FireCell> neighbors, int probCatch) {
		super(neighbors);
		myRandom = new Random();
		myColor = Color.RED;
		myProbCatch = probCatch;
	}

	public FireCellBurning(int probCatch){
		myColor = Color.RED;
		myRandom = new Random();
		myProbCatch = probCatch;
	}
	@Override
	public FireCell checkStatus() {
		return new FireCellEmpty(myNeighbors);
	}

	@Override
	public FireCell checkNeighbor() {
		if(calculateProbability()){
			return new FireCellBurning(myNeighbors, myProbCatch);
		}
		else{
			return null;
		}
	}

	private boolean calculateProbability() {
		return myRandom.nextInt(100) <= myProbCatch;
				
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	
}
