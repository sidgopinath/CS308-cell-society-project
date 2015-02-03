package model;

import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is on fire for the fire sim.
 * @author Sunjeev
 *
 */

public class FireSquareBurning extends FireSquare{

	private int myProbCatch;
	private Random myRandom;
	
	public FireSquareBurning(List<FireSquare> neighbors, int probCatch) {
		super(neighbors);
		myRandom = new Random();
		myColor = Color.RED;
		myProbCatch = probCatch;
	}

	public FireSquareBurning(int probCatch){
		myColor = Color.RED;
		myRandom = new Random();
		myProbCatch = probCatch;
	}
	@Override
	public FireSquare chechStatus() {
		return new FireSquareEmpty(myNeighbors);
	}

	@Override
	public FireSquare checkNeighbor() {
		if(calculateProbability()){
			return new FireSquareBurning(myNeighbors, myProbCatch);
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
