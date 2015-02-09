package model.simulations;

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

    public SimulationSegregation(Map<String, String> paramMap,
                                 Map<String,String> styleMap,
                                 Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, styleMap,grid, simScreen);
        myRandom = new Random();
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
    	try{
    		mySatisfaction = Double.parseDouble(paramMap.get("satisfaction"));
    	}
    	catch(Exception e){
    		paramMap.put("satisfaction", "0.3");
    		System.out.println("No satisfaction value. Using default.");
    		parseMap(paramMap);	
    	}
        
    }

    @Override
    public void updateGrid() {
        updateNeighbors();
        for (int j = 0; j < myPatchGrid.length; j++) {
            for (int i = 0; i < myPatchGrid[0].length; i++) {
            	myPatchGrid[j][i].getCell().update();
                if ((myPatchGrid[j][i].getCell().viewProperties().get("satisfied").intValue()) != 1) {
                    moveAgent(j, i, myPatchGrid[j][i].getCell());
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
            if (myPatchGrid[rColumn][rRow].getCell().viewProperties().get("empty").intValue() == 1) {
                myPatchGrid[rColumn][rRow].setCell(clone);
                myPatchGrid[j][i].setCell(new AgentCellEmpty(myPatchGrid[rColumn][rRow].getCell().
                                                             viewProperties().get("satisfactionRate").intValue()));
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
