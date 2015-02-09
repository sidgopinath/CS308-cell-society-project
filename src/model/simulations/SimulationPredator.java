package model.simulations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.cells.Cell;
import model.cells.PredatorCell;
import model.cells.PredatorCellEmpty;
import model.cells.PredatorCellFish;
import model.cells.PredatorCellShark;
import view.SimulationScreen;
import javafx.scene.paint.Color;

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
                myGrid[i][j].setCoords(j, i);
            }
        }
        movedGrid = new boolean[gridWidth][gridLength];
    }

    public void parseMap(Map<String,String> paramMap){
        breedingPeriod = Integer.parseInt(paramMap.get("breedingPeriod"));
        sharkLife = Integer.parseInt(paramMap.get("sharkLife"));
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
                    Cell currentSquare = myGrid[row][column];
                    updateNeighborSquare(currentSquare, row, column);
                    Cell updateSquare = currentSquare.update();
                    //Check if currentSquare moved as result of update
                    if(!(currentSquare.getX() == column && currentSquare.getY() ==row)){
                        movedGrid[currentSquare.getY()][currentSquare.getX()] = true;
                        movedGrid[updateSquare.getY()][updateSquare.getX()] = true;
                        
                        myGrid[currentSquare.getY()][currentSquare.getX()] = currentSquare;
                        
                        //Check if need to breed
                        if(currentSquare.viewProperties().get("myCurrentBreeding") != 0){
                            myGrid[row][column] = new PredatorCellEmpty(-1);
                            myGrid[row][column].setCoords(column, row);
                        } else{
                            myGrid[row][column] = updateSquare;
                        }
                        System.out.println("I went from row:" + row + " column: " + column);
                        System.out.println("to row: " + currentSquare.getY()
                                           + " column: " +currentSquare.getX()) ;
                    }else{ //Cell had no space to move/starved
                        myGrid[row][column] = updateSquare;
                        movedGrid[row][column] = true;
                    }
                } else{
                    continue;
                }
            }
        }
        updateColorGrid();
    }

    public void updateNeighborSquare(Cell square, int row, int column){
        Cell up;
        Cell down;
        Cell left;
        Cell right;
        int i = row;
        int j = column;
        if(i==0){
            up=myGrid[gridWidth-1][j];
        }else{
            up=myGrid[i-1][j];
        }

        if(i==gridWidth-1){
            down = myGrid[0][j];
        }else{
            down = myGrid[i+1][j];
        }

        if(j==0){
            left = myGrid[i][gridLength-1];
        }else{
            left = myGrid[i][j-1];
        }

        if(j==gridLength-1){
            right = myGrid[i][0];
        }else{
            right = myGrid[i][j+1];
        }
        List<Cell> neighborList = new ArrayList<Cell>();
        neighborList.add(up);
        neighborList.add(down);
        neighborList.add(left);
        neighborList.add(right);
        square.setNeighbors(neighborList);
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new PredatorCellFactory(breedingPeriod, sharkLife);
    }

}

