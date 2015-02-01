package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SquarePredator{


    private int myBreedingPeriod;
    private List<SquarePredator> myPredatorNeighbors;
    private int myX;
    private int myY;

    // TODO: Find a good way to return state to simulation object
    public abstract int getState();
    
    public SquarePredator (int breedingPeriod, int xCoord, int yCoord) {
        myBreedingPeriod = breedingPeriod;
        myX = xCoord;
        myY = yCoord;
    }
    /*
     * Returns square that this square wants to move to
     */
    public abstract SquarePredator moveSquare();
    
    /*
     * Returns new square as a result of reeding
     */
    public SquarePredator breedSquare(){
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
    
    
    public abstract SquarePredator getChildSquare(int x, int y, 
                                                  int breedingPeriod);
    
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    public void updateNeighbors(List<SquarePredator> neighbors){
        myPredatorNeighbors = neighbors;
    }

    /**
     *  Updates cell according to rules of Wa-Tor simulation. 
     */
    public void updateSquare(){
        
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
