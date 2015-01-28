package model;

import java.util.List;

public class SquareFire {

	private List<SquareFire> myNeighbors;
	
	/**
	 * @param neighbors is a list of neighbors passed in by its simulation class
	 */
	public SquareFire(List<SquareFire> neighbors){
		myNeighbors = neighbors;
	}
	
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public SquareFire update(){
		return null;
		
	}
}
