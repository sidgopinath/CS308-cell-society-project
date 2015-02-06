package model.cells;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class Cell {

	protected List<Cell> myNeighbors;
	
	public Cell(){
	}
	
	public void setNeighbors(ArrayList<Cell> neighbors){
		myNeighbors = neighbors;
	}
	public abstract Cell update();
	public abstract Color getColor();
}
