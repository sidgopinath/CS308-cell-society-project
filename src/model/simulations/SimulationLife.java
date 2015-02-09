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

    public SimulationLife(Map<String, String> paramMap, Map<String,String>
     styleMap, Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, styleMap, grid, simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
    }


    @Override
    public void updateGrid() {
        updateNeighbors();
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                myPatchGrid[j][i].setCell(myPatchGrid[j][i].getCell().update());
            }
        }
        updateColorGrid();
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new LifeCellFactory();
    }

}
