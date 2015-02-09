package model.simulations;

import java.util.Map;
import model.cells.Cell;
import model.cells.PredatorCellEmpty;
import view.SimulationScreen;

/**
 * Subclass of Simulation for the Predator-Prey CA simulation.
 * Contains methods and data necessary to run simulation
 * @author Janan
 *
 */
public class SimulationPredator extends Simulation {
    private int sharkLife;
    private int breedingPeriod;
    private boolean[][] movedGrid;

    public SimulationPredator(Map<String,String> paramMap,
                              Map<String,String> styleMap,Integer[][] initGrid,
                              SimulationScreen simscreen){
        super(paramMap, styleMap,initGrid, simscreen);
        for(int i=0; i<gridWidth; i++){
            for(int j=0; j < gridLength; j++){
                myPatchGrid[i][j].getCell().setCoords(j, i);
            }
        }
        movedGrid = new boolean[gridWidth][gridLength];
    }

    public void parseMap(Map<String,String> paramMap){
    	try{
    		breedingPeriod = Integer.parseInt(paramMap.get("breedingPeriod"));
    		sharkLife = Integer.parseInt(paramMap.get("sharkLife"));
    	}
    	catch(Exception e){
    		paramMap.put("breedingPeriod", "5");
    		paramMap.put("sharkLife", "3");
    		System.out.println("No value given for breedingPeriod or sharkLife.");
    		System.out.println("Default values used for both variables.");
    		parseMap(paramMap);
    	}
    }

    /*
     * Update grid at step and updates corresponding view
     */
    public void updateGrid(){
        for(int row=0; row<gridWidth; row++){
            for(int column=0; column<gridLength;column++){
                movedGrid[row][column] = false;
            }
        }

        for(int row = 0; row < gridWidth; row++){
            for(int column=0; column<gridLength;column++){
                if(!movedGrid[row][column]){
                    Cell currentSquare = myPatchGrid[row][column].getCell();
                    updateNeighborSquare(row, column);
                    Cell updateSquare = currentSquare.update();
                    //Check if currentSquare moved as result of update
                    if(!(currentSquare.getX() == column && currentSquare.getY() ==row)){
                        movedGrid[currentSquare.getY()][currentSquare.getX()] = true;
                        movedGrid[updateSquare.getY()][updateSquare.getX()] = true;
                        
                        myPatchGrid[currentSquare.getY()][currentSquare.getX()].setCell(currentSquare);
                        
                        //Check if need to breed
                        if(currentSquare.viewProperties().get("myCurrentBreeding") != 0){
                            myPatchGrid[row][column].setCell(new PredatorCellEmpty(-1));
                            myPatchGrid[row][column].getCell().setCoords(column, row);
                        } else{
                            myPatchGrid[row][column].setCell(updateSquare);
                        }
//                        System.out.println("I went from row:" + row + " column: " + column);
//                        System.out.println("to row: " + currentSquare.getY()
//                                           + " column: " +currentSquare.getX()) ;
                    }else{ //Cell had no space to move/starved
                        myPatchGrid[row][column].setCell(updateSquare);
                        movedGrid[row][column] = true;
                    }
                } else{
                    continue;
                }
            }
        }
        updateColorGrid();
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new PredatorCellFactory(breedingPeriod, sharkLife);
    }

}

