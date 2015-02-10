package view;

import java.util.ArrayList;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import controller.CellSocietyController;

/**
 	* This class begins setting up the general simulation scene. This
	* includes things like adding the four buttons at the top that load, step
	* through, speed up, and stop/start the simulation. Note: all strings used
	* to create buttons must be retrieved from a .properties file. 
	* @author Sunjeev
 */

public class SimulationScreen {
	private CellSocietyController myController;
	private int myWidth;
	private int myHeight;
	private BorderPane root;
	private ArrayList<Button> myButtons;
	private HBox myTop;
	private Map<String, String> myStyles;
	private Shape[][] myColorGrid;

	public void initSimScreen(int width, int height, CellSocietyController controller, Map<String, String> styleMap){
		myController = controller;
		root = new BorderPane();
		myWidth = width;
		myHeight = height;
		
		myStyles = styleMap;
		
		TopMenu topMenu = new TopMenu(myWidth, myHeight, myController);
		myButtons = new ArrayList<>(topMenu.createTopButtons());
		
		root.setTop(addButtons());
	}


	public BorderPane getNode(){
		return root;
	}

	private HBox addButtons() {
		myTop = new HBox();
		myTop.setPrefHeight(myHeight/20);
		myTop.setPrefWidth(myWidth);
		myTop.getChildren().addAll(myButtons);
		for(Button button: myButtons){
			button.setPrefWidth(myWidth / (myButtons.size()));
			button.setPrefHeight(myTop.getPrefHeight());
		}
		return myTop;
	}

	public void createOptionsPanel(String[] names, double[] value, double[] min, double[] max){
		SideMenu sideMenu = new SideMenu(myWidth, myHeight, names, value, min, max);
		sideMenu.createOptionWindow();
	}
	

	/**
	 * must be passed a grid of some type so that it can determine the colors of
	 * each square It will then go through each square and set its appropriate
	 * color
	 * 
	 * @param colorGrid
	 */
	public void updateScreen(Color[][] colorGrid) {
		for (int j = 0; j < colorGrid.length; j++) {
			for (int i = 0; i < colorGrid[0].length; i++) {
				// get color and update square with that color
				myColorGrid[j][i].setFill(colorGrid[j][i]);
			}
		}
	}

	/**
	 * Called from the simulation. If it is a triangle type, it forms triangles, if it is rectangular it forms
	 * rectangles
	 * @param gridHeight
	 * @param gridWidth
	 */
	public void initSimView(int gridHeight, int gridWidth) {
		Group simulation = new Group();
		myColorGrid = new Shape[gridHeight][gridWidth];
		ShapeGrid grid; 
		if(myStyles.get("cellShape").equals("square")){
			grid = new SquareGrid(gridWidth, gridHeight, myColorGrid);
			double heightOfSquare = (myHeight - myTop.getPrefHeight()) / gridHeight;
			double widthOfSquare = myWidth/ gridWidth;
			simulation = grid.createGrid(myStyles.get("gridOutline").equals("yes"), heightOfSquare, widthOfSquare);
		}
			
			
			
			
//		if(myStyles.get("cellShape").equals("triangle")){
//			Polygon tri = new Polygon(0.0, 0.0, 
//									  myWidth/gridWidth - 1, 0.0, 
//									  (myWidth/gridWidth - 1)/2, (myHeight - myTop.getPrefHeight())/ gridHeight - 1);
//			tri.setRotate(rotate);
//			tri.setFill(Color.GREEN);
//			if(myStyles.get("gridOutline").equals("yes")){
//				tri.setStroke(Color.BLACK);
//				tri.setTranslateX((i * (myWidth/gridWidth-1) - tri.getStrokeWidth())/2);
//				tri.setTranslateY(j * (myHeight - myTop.getPrefHeight())/ gridHeight - 1);
//			}
//			simulation.getChildren().add(tri);
//			myColorGrid[j][i] = tri;
//			rotate += 180;
//		}
//			}
//		}
		root.setCenter(simulation);
	}

}
