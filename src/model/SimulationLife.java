package model;

import java.util.ArrayList;
import java.util.Map;

import view.SimulationScreen;
import javafx.scene.paint.Color;

public class SimulationLife extends Simulation {

	private LifeSquare[][] myGrid;

	public SimulationLife(SimulationScreen simScreen, Map<String, String> paramMap, Integer[][] grid) {
		super(simScreen);
		runSim(paramMap, grid);
	}
	
	@Override
	public void runSim(Map<String, String> paramMap, Integer[][] grid) {
		myGrid = new LifeSquare[grid.length][grid[0].length];
		fillGrid(paramMap, grid);
		myView.initSimView(myGrid.length, myGrid[0].length);
		updateGrid();
	}

	@Override
	void fillGrid(Map<String, String> paramMap, Integer[][] grid) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[j].length; i++){
				if(grid[j][i] == 0){
					myGrid[j][i] = new LifeSquareDead();
				}
				else{
					myGrid[j][i] = new LifeSquareAlive();
				}
			}
		}
		updateNeighbors();
	}

	/**
	 * This code is pretty awful as far as if statements go
	 * REFACTOR!
	 */
	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				ArrayList<LifeSquare> neighbors = new ArrayList<>();
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
				if(i+1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i+1]);
				}
				if(i+1 < myGrid[0].length && j-1 < myGrid.length){
					neighbors.add(myGrid[j-1][i+1]);
				}
				if(i-1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i-1]);
				}
				if(i-1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i-1]);
				}
				myGrid[j][i].setNeighbors(neighbors);
			}
		}
		updateGrid();
	}

	@Override
	public void updateGrid() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				myGrid[j][i] = myGrid[j][i].update();
			}
		}
		updateColorGrid();
		updateNeighbors();
	}

	@Override
	void updateColorGrid() {
		Color[][] colorGrid = new Color[myGrid.length][myGrid[0].length];
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[j].length; i++){
				colorGrid[j][i] = myGrid[j][i].getColor();
			}
		}
		myView.updateScreen(colorGrid);
	}

}
