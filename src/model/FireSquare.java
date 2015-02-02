package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class FireSquare {

	protected List<FireSquare> myNeighbors;
	protected Color myColor;
	
	/**
	 * @param neighbors is a list of neighbors passed in by its simulation class
	 */
	public FireSquare(List<FireSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	public FireSquare(){
	}
	public void setNeighbors(ArrayList<FireSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public FireSquare update(){
		return this.chechStatus();
	}
	
	public abstract FireSquare chechStatus();
	public abstract FireSquare checkNeighbor();
	public abstract Color getColor();
}
