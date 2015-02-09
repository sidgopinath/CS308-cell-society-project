package model.simulations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    //TODO: fix alreadyMoved so as to not require the X and Y coordinates
    private Set<PredatorCell> alreadyMoved = new HashSet<PredatorCell>();


    public SimulationPredator(Map<String,String> paramMap,Integer[][] initGrid,
                              SimulationScreen simscreen){
        super(paramMap, initGrid, simscreen);
    }

    public void parseMap(Map<String,String> paramMap){
    	if(!paramMap.containsKey("breedingPeriod") || paramMap.get("breedingPeriod") == null){
        	//throw exception
        }
    	if(!paramMap.containsKey("sharkLife") || paramMap.get("sharkLife") == null){
        	//throw exception
        }
    	breedingPeriod = Integer.parseInt(paramMap.get("breedingPeriod"));
        sharkLife = Integer.parseInt(paramMap.get("sharkLife"));
    }

    //TODO: need to rewrite completely with patches
    public void move(PredatorCell square,int x, int y){
        PredatorCell childSquare = square.breedSquare();
        PredatorCell moveTo = square.moveSquare();
        if(square.equals(moveTo)){
            return;
        }
        grid[moveTo.getY()][moveTo.getX()] = square;
        if(!square.isBreeding()){
            grid[y][x] = new PredatorCellEmpty(-1);
        } else{
            grid[y][x] = childSquare;
        }
        alreadyMoved.add(square);
        alreadyMoved.add(grid[y][x]);
    }

    /*
     * Update grid at step and updates corresponding view
     */
    public void updateGrid(){
        for(int row = 0; row < gridWidth; row++){
            for(int column=0;column<gridLength;column++){
                PredatorCell currentSquare = grid[row][column];
                if(!alreadyMoved.contains(currentSquare)){
                    currentSquare.updateSquare();

                    //Check if need to starve shark
                    if(currentSquare.hasStarved()){
                        grid[row][column] = new PredatorCellEmpty(-1);
                        continue;
                    }
                    updateNeighborSquare(currentSquare,row,column);
                    move(currentSquare,column,row);
                } else{
                    continue;
                }
            }
        }
        alreadyMoved.clear();
        updateColorGrid();

    }

    public void updateNeighborSquare(PredatorCell square, int row, int column){
        PredatorCell up;
        PredatorCell down;
        PredatorCell left;
        PredatorCell right;
        int i = row;
        int j = column;
        if(i==0){
            up=grid[grid.length-1][j];
        }else{
            up=grid[i-1][j];
        }

        if(i==grid.length-1){
            down = grid[0][j];
        }else{
            down = grid[i+1][j];
        }

        if(j==0){
            left = grid[i][grid[0].length-1];
        }else{
            left = grid[i][j-1];
        }

        if(j==grid[0].length-1){
            right = grid[i][0];
        }else{
            right = grid[i][j+1];
        }
        List<PredatorCell> neighborList = new ArrayList<PredatorCell>();
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

