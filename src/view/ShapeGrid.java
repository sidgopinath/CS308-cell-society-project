package view;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public abstract class ShapeGrid {

	protected double myGridWidth;
	protected double myGridHeight;
	private Shape[][] myColorGrid;

	public ShapeGrid(double gridWidth, double gridHeight, Shape colorGrid[][]){
		myGridWidth = gridWidth;
		myGridHeight = gridHeight;
		myColorGrid = colorGrid;
	}
	
	public Shape[][] getColorGrid(){
		return myColorGrid;
	}
	
	public Group createGrid(boolean stroke, double heightOfShape, double widthOfShape){
		Group simulation = new Group();
		for (int j = 0; j < myGridHeight; j++) {
			for (int i = 0; i < myGridWidth; i++) {
				Shape s = addShape(stroke, heightOfShape, widthOfShape, i, j);
				myColorGrid[j][i] = s;
				simulation.getChildren().add(s);
			}
		}
		return simulation;
	}
	
	public abstract Shape addShape(boolean stroke, double heightOfSquare, double widthOfSquare, int i, int j);
}
