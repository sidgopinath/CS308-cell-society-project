package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public abstract class SquarePredator{

    private int myBreedingPeriod;
    private int currentBreeding;
    private List<SquarePredator> myPredatorNeighbors;
    private int myX;
    private int myY;

    // TODO: Find a good way to return state to simulation object
    public abstract int getState();
    
    public SquarePredator (int breedingPeriod, int xCoord, int yCoord) {
        myBreedingPeriod = breedingPeriod;
        currentBreeding = breedingPeriod;
        myX = xCoord;
        myY = yCoord;
    }
    
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    public void updateNeighbors(List<SquarePredator> neighbors){
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
    public abstract SquarePredator moveSquareTo();
    
    public abstract Color getColor();
    
    public SquarePredator moveSquare(){
        SquarePredator square = moveSquareTo();
        myX = square.getX();
        myY = square.getY();
        return square;
    }
    
    
    /*
     * Returns new square as a result of breeding. Will return null if no square 
     * available to breed.
     */
    public SquarePredator breedSquare(){
        if(currentBreeding != 0){
            return null;
        }
        Random squarePick = new Random();
        List<SquarePredator> moveableNeighbors = new ArrayList<SquarePredator>();
        for(SquarePredator square: myPredatorNeighbors){
            if(square.isMovable()){
                moveableNeighbors.add(square);
            }
        }
        if(moveableNeighbors.isEmpty()){
            return null;
        }
        SquarePredator breedLocation =
        moveableNeighbors.get(squarePick.nextInt(moveableNeighbors.size()));
        return getChildSquare(breedLocation.getX(),breedLocation.getY(), myBreedingPeriod);
    };
    
    /*
     * Public method to allow subclasses to decrement breeding countdown on update
     */
    public void decrementBreeding(){
        myBreedingPeriod--;
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
    public abstract SquarePredator getChildSquare(int x, int y, 
                                                  int breedingPeriod);
    
    
    /*
     * Boolean to indicate starvation for a predator square, set to false
     * by default for normal squares.
     */
    public boolean hasStarved(){
        return false;
    }
    
    public List<SquarePredator> getMyNeighbors(){
        return myPredatorNeighbors;
    }
    
    public int getX(){
        return myX;
    }
    
    public int getY(){
        return myY;
    }
    
    /*
     * Returns whether or not cell is edible by another shark
     */
    public abstract boolean isEdible();
    /*
     * Returns whether or not cell can be moved to
     */
    public abstract boolean isMovable();

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + myX;
        result = prime * result + myY;
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SquarePredator other = (SquarePredator) obj;
        if (myX != other.myX) return false;
        if (myY != other.myY) return false;
        return true;
    }
}
