package model.simulations;

import java.util.ArrayList;
import java.util.Map;

import model.cells.LifeCell;
import model.cells.LifeCellAlive;
import model.cells.LifeCellDead;
import view.SimulationScreen;
import javafx.scene.paint.Color;

/**
 * 
 * @author Sunjeev and Sid
 *
 */

public class SimulationLife extends Simulation {

    private LifeCell[][] myGrid;

    public SimulationLife(Map<String, String> paramMap, Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, grid, simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void fillGrid(Integer[][] grid) {
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                if(grid[j][i] == 0){
                    myGrid[j][i] = new LifeCellDead();
                }
                else{
                    myGrid[j][i] = new LifeCellAlive();
                }
            }
        }
        updateColorGrid();
    }

    /**
     * This code is pretty awful as far as if statements go
     * REFACTOR! 
     */
    void updateNeighbors() {
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                ArrayList<LifeCell> neighbors = new ArrayList<>();
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
                if(i-1 >= 0 && j+1 < gridWidth){
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
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                myGrid[j][i] = myGrid[j][i].update();
            }
        }
        updateColorGrid();
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
    public void setupGrid() {
        myGrid = new LifeCell[gridWidth][gridLength];
    }

}
