package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGrid extends ShapeGrid{

	public SquareGrid(double gridWidth, double gridHeight, Shape[][] colorGrid) {
		super(gridWidth, gridHeight, colorGrid);
	}

	@Override
	public Shape addShape(boolean stroke, double heightOfShape, double widthOfShape, int i, int j) {
		Rectangle rect = new Rectangle();
		rect.setFill(Color.WHITE);
		if(stroke){
			rect.setWidth(widthOfShape - rect.getStrokeWidth());
			rect.setHeight(heightOfShape - rect.getStrokeWidth());
			rect.setStroke(Color.BLACK);
			rect.setTranslateX(i * (rect.getWidth() + rect.getStrokeWidth()));
			rect.setTranslateY(j * (rect.getHeight() + rect.getStrokeWidth()));
		}
		else{
			rect.setWidth(widthOfShape);
			rect.setHeight(heightOfShape);
			rect.setTranslateX(i * rect.getWidth());
			rect.setTranslateY(j * rect.getHeight());
		}
		return rect;
	}

}
