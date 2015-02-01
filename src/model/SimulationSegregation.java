package model;

import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation {

	private AgentSquare[][] myGrid;
	private Random myRandom;

	public SimulationSegregation(Map<String,String> paramMap){
		super();
		runSim(paramMap);
	}
	
	@Override
	void fillGrid(Map<String, String> paramMap) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0; i < myGrid[0].length; i++){
				//to be determined by paramMap if it's O or X, or blank
				myGrid[j][i] = new AgentSquareEmpty(null);
			}
		}
		//pass neighbors to all agentSquares
		updateNeighbors();
	}

	@Override
	void updateGrid() {
		AgentSquare[][] clone = getMyGridClone();
		for(int j = 0; j < clone.length; j++){
			for(int i = 0; i < clone[0].length; i++){
				if(!clone[j][i].isSatisfied()){
					//agent not satisfied
					//move to random empty agent square
					moveAgent(j, i, clone[j][i]);
				}
			}
		}
		updateColorGrid();
		updateNeighbors();
	}

	private void moveAgent(int j, int i, AgentSquare agent) {
		myGrid[j][i] = new AgentSquareEmpty();
		//could be dangerous, might want to add safeguard
		int count = 0;
		while(true){
			int rColumn = myRandom.nextInt(myGrid.length);
			int rRow = myRandom.nextInt(myGrid[0].length);
			if(myGrid[rColumn][rRow].isEmpty()){
				myGrid[j][i] = agent;
				break;
			}
			if(count > 100){
				System.out.println("agent cannot move");
				break;
			}
			count++;
		}
	}

	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0; i < myGrid[0].length; i++){
				//pass all neighbors by indices
				//TODO: implement this method
			}
		}
		updateGrid();
	}

	@Override
	void runSim(Map<String, String> paramMap) {
		myGrid = new AgentSquare[5][5];
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
		//create color grid based off of agent square's state
		updateView(null);
		
	}
	
	//http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
	private AgentSquare[][] getMyGridClone(){
		AgentSquare[][] clone = new AgentSquare[myGrid.length][];
		for(int j = 0; j < myGrid.length; j++){
			clone[j] = myGrid[j].clone();
		}
		return clone;
	}
}
