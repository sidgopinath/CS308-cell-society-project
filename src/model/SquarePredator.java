package model;

import java.util.List;
import java.util.Random;

public abstract class SquarePredator{


    private int myBreedingPeriod;
    private List<SquarePredator> myPredatorNeighbors;



    public abstract int getState();
    
    public SquarePredator (int breedingPeriod) {
        myBreedingPeriod = breedingPeriod;
    }
    
    
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    public void updateNeighbors(List<SquarePredator> neighbors){
        myPredatorNeighbors = neighbors;
    }

    /**
     *  Updates cell according to rules of Wa-Tor simulation. States of nearby cells
     *  are given by neighborFish and neighborEmpty
     */
    public void updateSquare(){
        
    }

    public abstract boolean isEdible();
    public abstract boolean isMovable();

}
