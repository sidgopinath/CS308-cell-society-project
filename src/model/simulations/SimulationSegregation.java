package model.simulations;

import java.util.ArrayList;
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
    private AgentCell[][] myGrid;
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
    
    @Override
    void fillGrid(Integer[][] grid) {
        for (int j = 0; j < gridWidth; j++) {
            for (int i = 0; i < gridLength; i++) {
                if(grid[j][i] == 0){
                    myGrid[j][i] = new AgentCellEmpty(mySatisfaction);
                }
                else if(grid[j][i] == 1){
                    myGrid[j][i] = new AgentCellO(mySatisfaction);
                }
                else if(grid[j][i] == 2){
                    myGrid[j][i] = new AgentCellX(mySatisfaction);
                }
            }
        }
        updateColorGrid();
    }

    void updateNeighbors() {
        for (int j = 0; j < gridWidth; j++) {
            for (int i = 0; i < gridLength; i++) {
                ArrayList<Cell> neighbors = new ArrayList<>();
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

    private void moveAgent(int j, int i, AgentCell agent) {
        // could be dangerous, might want to add safeguard
        int count = 0;
        while (true) {
            int rColumn = myRandom.nextInt(gridWidth);
            int rRow = myRandom.nextInt(gridLength);
            if (myGrid[rColumn][rRow].isEmpty()) {
                //				System.out.println("rColumn: " + rColumn);
                //				System.out.println("rRow: " + rRow);
                //				System.out.println("moved one agent");
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
    void updateColorGrid() {
        Color[][] colorGrid = new Color[gridWidth][gridLength];
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                colorGrid[j][i] = myGrid[j][i].getColor();
            }
        }
        myView.updateScreen(colorGrid);
    }

    @Override
    void setupGrid() {
        myGrid = new AgentCell[gridWidth][gridLength];
    }
}
