package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * Abstract superclass for squares in the Predator-Prey simulation
 * Contains data and methods unique to Predator-Prey simulation
 * @author Janan
 *
 */

public abstract class PredatorCell extends Cell{


    private int myBreedingPeriod;
    private int currentBreeding;
    private List<PredatorCell> myPredatorNeighbors;

    
    public PredatorCell (int breedingPeriod) {
        myBreedingPeriod = breedingPeriod;
        currentBreeding = breedingPeriod;
    }
    
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    
    public void setNeighbors(List<PredatorCell> neighbors){
        myPredatorNeighbors = neighbors;
    }

    /**
     *  Updates constants of the cell that change with time, like breeding status
     *  and starvation status 
     */
    public abstract void updateSquare();
    
    /*
     * Returns square that this square wants to move to, unless all squares
     * are full, in which case it returns itself.
     */
    public abstract PredatorCell moveSquareTo();
    
    
    public PredatorCell moveSquare(){
        PredatorCell square = moveSquareTo();
        return square;
    }
    
    
    /*
     * Returns new square as a result of breeding. Will return null if no square 
     * available to breed.
     */
    public PredatorCell breedSquare(){
        return getChildSquare(myBreedingPeriod);
    };
    
    /*
     * Public method to allow subclasses to decrement breeding countdown on update
     */
    public void decrementBreeding(){
        currentBreeding--;
    }
    
    /*
     * Indicate whether a square has reached its breeding period
     */
    public boolean isBreeding(){
        if(currentBreeding == 0){
            currentBreeding = myBreedingPeriod;
            return true;
        }
        return false;
    }
    
    /*
     * Returns a new square that should be placed in the grid as a result of breeding
     */
    public abstract PredatorCell getChildSquare(int breedingPeriod);
    
    
    /*
     * Boolean to indicate starvation for a predator square, set to false
     * by default for normal squares.
     */
    public boolean hasStarved(){
        return false;
    }
    
    public List<PredatorCell> getMyNeighbors(){
        return myPredatorNeighbors;
    }
    
    
    /*
     * Returns whether or not cell is edible by another shark
     */
    public abstract boolean isEdible();
    /*
     * Returns whether or not cell can be moved to
     */
    public abstract boolean isMovable();
}
