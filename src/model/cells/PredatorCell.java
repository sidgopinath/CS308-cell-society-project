package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

public abstract class PredatorCell{

    private int myBreedingPeriod;
    private int currentBreeding;
    private List<PredatorCell> myPredatorNeighbors;
    private int myX;
    private int myY;

    
    public PredatorCell (int breedingPeriod, int xCoord, int yCoord) {
        myBreedingPeriod = breedingPeriod;
        currentBreeding = breedingPeriod;
        myX = xCoord;
        myY = yCoord;
    }
    
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    public void updateNeighbors(List<PredatorCell> neighbors){
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
    
    public abstract Color getColor();
    
    public PredatorCell moveSquare(){
        PredatorCell square = moveSquareTo();
        myX = square.getX();
        myY = square.getY();
        return square;
    }
    
    
    /*
     * Returns new square as a result of breeding. Will return null if no square 
     * available to breed.
     */
    public PredatorCell breedSquare(){
        return getChildSquare(this.getX(),this.getY(), myBreedingPeriod);
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
    public abstract PredatorCell getChildSquare(int x, int y, 
                                                  int breedingPeriod);
    
    
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
        PredatorCell other = (PredatorCell) obj;
        if (myX != other.myX) return false;
        if (myY != other.myY) return false;
        return true;
    }
}
