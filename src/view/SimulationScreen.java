package view;

import java.util.ArrayList;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
	private GridPane myGridPane;
	private BorderPane root;
	private ArrayList<Button> myButtons;
	private HBox myTop;

	public void initSimScreen(int width, int height, CellSocietyController controller, Map<String, String> styleMap){
		myController = controller;
		root = new BorderPane();
		myWidth = width;
		myHeight = height;
		myGridPane = new GridPane();
		
		TopMenu topMenu = new TopMenu(myWidth, myHeight, myController);
		myButtons = new ArrayList<>(topMenu.createTopButtons());
		
		root.setTop(addButtons());
		root.setCenter(myGridPane);
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
				getChild(j, i).setFill(colorGrid[j][i]);
			}
		}
	}

	/**
	 * goes through myGridPane and creates a new rectangle object at each spot
	 * in the grid
	 * @param gridHeight
	 * @param gridWidth
	 */
	public void initSimView(int gridHeight, int gridWidth) {
		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				Rectangle rect = new Rectangle();
				rect.setFill(Color.WHITE);
				rect.setStroke(Color.BLACK);
				rect.setWidth(myWidth/ gridWidth - rect.getStrokeWidth());
				rect.setHeight((myHeight - myTop.getPrefHeight()) / gridHeight - rect.getStrokeWidth());
				myGridPane.add(rect, i, j);
			}
		}
	}

	// http://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
	private Shape getChild(int row, int column) {
		for (Node child : myGridPane.getChildren()) {
			if (GridPane.getRowIndex(child) == row
					&& GridPane.getColumnIndex(child) == column) {
				return (Shape) child;
			}
		}
		return null;

	}
}
