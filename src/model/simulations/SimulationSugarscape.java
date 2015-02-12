package model.simulations;

import java.util.Map;
import model.cells.Cell;
import model.cells.SugarCellEmpty;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import view.SimulationScreen;

public class SimulationSugarscape extends Simulation {
    private int sugarGrowBackRate;
    private int sugarGrowBackInterval;
    private int sugarMetabolism;
    private int vision;
    private int initialSugar;
    private int numAgents;

    public SimulationSugarscape(Map<String, String> paramMap, Map<String,String> styleMap,
                                Integer[][] grid,
                                SimulationScreen simScreen) {
        super(paramMap, styleMap, grid, simScreen);
    }

    @Override
    void parseMap(Map<String, String> paramMap) {
        sugarGrowBackRate = Integer.parseInt(paramMap.get("sugarGrowBackRate"));
        sugarGrowBackInterval = Integer.parseInt(paramMap.get("sugarGrowBackInterval"));
        sugarMetabolism = Integer.parseInt(paramMap.get("sugarMetabolism"));
        vision = Integer.parseInt(paramMap.get("vision"));
        initialSugar = Integer.parseInt(paramMap.get("initialSugar"));
        numAgents = Integer.parseInt(paramMap.get("numAgents"));
    }

    @Override
    void setupParameterControl () {
        String[] paramNames = {"sugarGrowBackRate","sugarGrowBackInterval","sugarMetabolism","vision"};
        double[] currentValues = {sugarGrowBackRate,sugarGrowBackInterval,sugarMetabolism,vision};
        double [] minValues = {1,1,1,1};
        double [] maxValues = {5,5,6,4};
        myView.createOptionsPanel(paramNames,currentValues, minValues, maxValues);
    }

    @Override
    public void updateGrid() {
        
        updateNeighbors();
        for(int i=0; i<gridWidth; i++){
            for(int j=0; j<gridLength; j++){
                Cell currentCell = myPatchGrid[i][j].getCell();
                currentCell.update();
                int newX = currentCell.getX();
                int newY = currentCell.getY();
                myPatchGrid[i][j].setCell(new SugarCellEmpty());
                myPatchGrid[newY][newX].setCell(currentCell);
                if(currentCell.viewProperties().get("sugar") <= 0){
                    myPatchGrid[newY][newX].setCell(new SugarCellEmpty());
                }
            }
        }
        updateColorGrid();
    }

    @Override
    public String patchType(){
        return "Sugar";
    }

    @Override
    AbstractCellFactory getCellFactory () throws ValueException {
        return new SugarCellFactory(vision, sugarMetabolism,initialSugar);
    }

}
