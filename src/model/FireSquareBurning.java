package model;

import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

public class FireSquareBurning extends FireSquare{

	
	private Random myRandom;
	
	public FireSquareBurning(List<FireSquare> neighbors) {
		super(neighbors);
		myRandom = new Random();
		myColor = Color.RED;
	}

	public FireSquareBurning(){
		myColor = Color.RED;
		myRandom = new Random();
	}
	@Override
	public FireSquare chechStatus() {
		return new FireSquareEmpty(myNeighbors);
	}

	@Override
	public FireSquare checkNeighbor() {
		if(calculateProbability()){
			return new FireSquareBurning(myNeighbors);
		}
		else{
			return null;
		}
	}

	private boolean calculateProbability() {
		return myRandom.nextInt(100) <= PROB_CATCH;
				
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	
}
