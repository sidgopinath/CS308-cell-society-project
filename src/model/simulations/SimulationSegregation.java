package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.cells.AgentCellEmpty;
import model.cells.Cell;
import view.SimulationScreen;

/**
 * 
 * @author Sunjeev and Sid
 *
 */

public class SimulationSegregation extends Simulation {

    private static final int SAFE_GUARD = 1000;
    private Random myRandom;
	private double mySatisfaction;

    public SimulationSegregation(Map<String, String> paramMap, Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, grid, simScreen);
        myRandom = new Random();
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
    	if(!paramMap.containsKey("satisfaction") || paramMap.get("satisfaction") == null){
        	//throw exception
        }
    	mySatisfaction = Double.parseDouble(paramMap.get("satisfaction"));
        
    }

    void updateNeighbors() {
        for (int j = 0; j < gridWidth; j++) {
            for (int i = 0; i < gridLength; i++) {
                List<Cell> neighbors = new ArrayList<Cell>();
                if(i + 1 < gridLength){
                    neighbors.add(myGrid[j][i + 1]);
                }
                if(i - 1 >= 0){
                    neighbors.add(myGrid[j][i - 1]);
                }
                if(j + 1 < gridWidth){
                    neighbors.add(myGrid[j + 1][i]);
                }
                if(j - 1 >= 0){
                    neighbors.add(myGrid[j - 1][i]);
                }
                if(i+1 < gridLength && j+1 < gridWidth){
                    neighbors.add(myGrid[j+1][i+1]);
                }
                if(i+1 < gridLength && j-1 >= 0){
                    neighbors.add(myGrid[j-1][i+1]);
                }
                if(i-1 >= 0  && j+1 < gridWidth){
                    neighbors.add(myGrid[j+1][i-1]);
                }
                if(i-1 >= 0 && j-1 >= 0){
                    neighbors.add(myGrid[j-1][i-1]);
                }
                myGrid[j][i].setNeighbors(neighbors);
            }
        }
    }

    @Override
    public void updateGrid() {
        updateNeighbors();
        Cell[][] clone = myGrid;
        for (int j = 0; j < clone.length; j++) {
            for (int i = 0; i < clone[0].length; i++) {
            	clone[j][i].update();
                if ((clone[j][i].viewProperties().get("satisfied").intValue()) != 1) {
                    moveAgent(j, i, clone[j][i]);
                }
            }
        }
        updateColorGrid();
    }

    private void moveAgent(int j, int i, Cell clone) {
        int count = 0;
        while (true) {
            int rColumn = myRandom.nextInt(gridWidth);
            int rRow = myRandom.nextInt(gridLength);
            if (myGrid[rColumn][rRow].viewProperties().get("empty").intValue() == 1) {
                myGrid[rColumn][rRow] = clone;
                myGrid[j][i] = new AgentCellEmpty(myGrid[rColumn][rRow].viewProperties().get("satisfactionRate").intValue());
                break;
            }
            if (count > SAFE_GUARD) {
                System.out.println("agent unable to move");
                break;
            }
            count++;
        }
    }

//    // http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
//    private Cell[][] getMyGridClone() {
//        Cell[][] clone = new Cell[gridWidth][];
//        for (int j = 0; j < gridWidth; j++) {
//        	clone[j] = myGrid[j].clone();
//        }
//        return clone;
//    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new AgentCellFactory(mySatisfaction);
    }
}
