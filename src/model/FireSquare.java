package model;

import java.util.List;

public class FireSquare {

	private List<FireSquare> myNeighbors;
	
	/**
	 * @param neighbors is a list of neighbors passed in by its simulation class
	 */
	public FireSquare(List<FireSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public FireSquare update(){
		return null;
		
	}
}
