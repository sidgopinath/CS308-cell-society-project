package model;

import java.util.ArrayList;
import java.util.Map;

import view.SimulationScreen;
import javafx.scene.paint.Color;

public class SimulationFire extends Simulation {

	private FireSquare[][] myGrid;

	public SimulationFire(Map<String,String> paramMap, Integer[][] grid, SimulationScreen simScreen){
		super(simScreen);
		runSim(paramMap, grid);
	}


	@Override
	void updateGrid() {
		updateNeighbors();
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				myGrid[j][i] = myGrid[j][i].update();
			}
		}
		updateColorGrid();
	}

	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				ArrayList<FireSquare> neighbors = new ArrayList<>();
				if(i + 1 < myGrid[0].length){
					neighbors.add(myGrid[j][i + 1]);
				}
				if(i - 1 > 0){
					neighbors.add(myGrid[j][i - 1]);
				}
				if(j + 1 < myGrid.length){
					neighbors.add(myGrid[j + 1][i]);
				}
				if(j - 1 > 0){
					neighbors.add(myGrid[j - 1][i]);
				}
				myGrid[j][i].setNeighbors(neighbors);
			}
		}
	}


	@Override
	 void updateColorGrid() {
		Color[][] colorGrid = new Color[myGrid.length][myGrid[0].length];
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0; i < myGrid[0].length; i++){
				colorGrid[j][i] = myGrid[j][i].getColor();
			}
		}
		myView.updateScreen(colorGrid);
	}
	
	@Override
	void fillGrid(Map<String, String> paramMap, Integer[][] grid) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				if(grid[j][i] == 0){
					myGrid[j][i] = new FireSquareEmpty();
				}
				if(grid[j][i] == 1){
					myGrid[j][i] = new FireSquareTree();
				}
				if(grid[j][i] == 2){
					myGrid[j][i] = new FireSquareBurning();
				}
			}
		}
		updateColorGrid();
		//all squares passed in first, then passed their neighbors
		
	}


	@Override
	void runSim(Map<String, String> paramMap, Integer[][] grid) {
		myGrid = new FireSquare[grid.length][grid[0].length];
		myView.initSimView(myGrid.length, myGrid[0].length);
		fillGrid(paramMap, grid);
		
	}
	
}
