package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.cells.AgentCell;
import model.cells.AgentCellEmpty;
import model.cells.AgentCellO;
import model.cells.AgentCellX;
import model.cells.Cell;
import javafx.scene.paint.Color;
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
        AgentCell[][] clone = getMyGridClone();
        for (int j = 0; j < clone.length; j++) {
            for (int i = 0; i < clone[0].length; i++) {
                if (!clone[j][i].isSatisfied()) {
                    moveAgent(j, i, clone[j][i]);
                }
            }
        }
        updateColorGrid();
    }

    private void moveAgent(int j, int i, AgentCell agent) {
        // could be dangerous, might want to add safeguard
        int count = 0;
        while (true) {
            int rColumn = myRandom.nextInt(gridWidth);
            int rRow = myRandom.nextInt(gridLength);
            if (myGrid[rColumn][rRow].isEmpty()) {
                myGrid[rColumn][rRow] = agent;
                myGrid[j][i] = new AgentCellEmpty();
                break;
            }
            if (count > SAFE_GUARD) {
                System.out.println("agent unable to move");
                break;
            }
            count++;
        }

    }

    // http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
    private AgentCell[][] getMyGridClone() {
        AgentCell[][] clone = new AgentCell[gridWidth][];
        for (int j = 0; j < gridWidth; j++) {
            clone[j] = myGrid[j].clone();
        }
        return clone;
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new AgentCellFactory(mySatisfaction);
    }
}
