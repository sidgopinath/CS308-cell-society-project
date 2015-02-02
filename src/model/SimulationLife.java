package model;

import java.util.Map;

import javafx.scene.paint.Color;

public class SimulationLife extends Simulation {

	private LifeSquare[][] myGrid;

	@Override
	void fillGrid(Map<String, String> paramMap) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				//TODO: add to this method
				//will vary depending on what's given
				myGrid[j][i] = new LifeSquareDead();
			}
		}
		updateNeighbors();
	}

	@Override
	void updateGrid() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				myGrid[j][i] = myGrid[j][i].update();
			}
		}
		updateColorGrid();
		updateNeighbors();
	}

	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				//TODO: implement this method with the correct neighbors by index
			}
		}
		updateGrid();
	}

	@Override
	public void runSim(Map<String, String> paramMap) {
		myGrid = new LifeSquare[5][5];
		fillGrid(paramMap);
		myView.initSimView(myGrid.length, myGrid[0].length);
		updateGrid();
	}

	@Override
	void updateView(Color[][] colorGrid) {
		myView.updateScreen(colorGrid);
	}

	@Override
	void updateColorGrid() {
		// TODO: create color grid from squares
		updateView(null);
		
	}

}
