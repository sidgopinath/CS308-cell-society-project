package model.simulations;

import java.util.ArrayList;
import java.util.Map;

import model.cells.FireCell;
import model.cells.FireCellBurning;
import model.cells.FireCellEmpty;
import model.cells.FireCellTree;
import javafx.scene.paint.Color;
import view.SimulationScreen;

public class SimulationFire extends Simulation {

    private int myProbCatch;

    public SimulationFire(Map<String,String> paramMap,Map<String,String> styleMap,
                          Integer[][] grid, SimulationScreen simScreen){
        super(paramMap,styleMap, grid,simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
        myProbCatch = Integer.parseInt(paramMap.get("probCatch"));
        
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

    void updateNeighbors() {
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                ArrayList<FireCell> neighbors = new ArrayList<>();
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
                myGrid[j][i].setNeighbors(neighbors);
            }
        }
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new FireCellFactory(myProbCatch);
    }
}
