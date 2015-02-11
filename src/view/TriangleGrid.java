package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleGrid extends ShapeGrid{

	public TriangleGrid(double gridWidth, double gridHeight, Shape[][] colorGrid) {
		super(gridWidth, gridHeight, colorGrid);
	}

	@Override
	public Shape addShape(boolean stroke, double heightOfShape,double widthOfShape, int i, int j) {
		Polygon tri = null;
		if(stroke){
			tri = makeNewStrokeTriangle(heightOfShape, widthOfShape);
			format(heightOfShape, widthOfShape, i, j, tri);
			translateTri(heightOfShape, widthOfShape, i, j, tri);
		}
		else{
			tri = makeNewTriangle(heightOfShape, widthOfShape);
			tri.setFill(Color.WHITE);
			translateTri(heightOfShape, widthOfShape, i , j, tri);  
		}
		return tri;
	}

	private Polygon makeNewTriangle(double heightOfShape, double widthOfShape) {
		Polygon tri;
		tri = new Polygon(0.0, 0.0, 
					widthOfShape, 0.0, 
					(widthOfShape)/2, heightOfShape);
		return tri;
	}


	private void translateTri(double heightOfShape, double widthOfShape, int i, int j, Polygon tri1) {
		if(isOdd(i)){
			tri1.setRotate(180);
		}
		if(isOdd(j)){
			tri1.setRotate(tri1.getRotate() - 180);
		}
		tri1.setTranslateX(i * (widthOfShape) / 2);
		tri1.setTranslateY(j * (heightOfShape));
	}

	private boolean isOdd(int i) {
		return i % 2 != 0;
	}

	private void format(double heightOfShape, double widthOfShape, int i,
			int j, Polygon tri1) {
		tri1.setFill(Color.WHITE);
		tri1.setStroke(Color.BLACK);
	}

	private Polygon makeNewStrokeTriangle(double heightOfShape, double widthOfShape) {
		return new Polygon(0.0, 0.0, 
   				(widthOfShape - 1), 0.0, 
   				(widthOfShape - 1)/2, heightOfShape - 1);
	}

}
