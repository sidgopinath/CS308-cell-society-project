package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.cells.Cell;
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

    public SimulationLife(Map<String, String> paramMap, Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, grid, simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
    }

    /**
     * This code is pretty awful as far as if statements go
     * REFACTOR! 
     */
    void updateNeighbors() {
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                List<Cell> neighbors = new ArrayList<>();
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
    AbstractCellFactory getCellFactory () {
        return new LifeCellFactory();
    }

}
