package model;

import java.util.List;
import java.util.Random;

public class SquarePredator extends Square {

    private int myLife;
    private int myBreedingPeriod;
    private List<Square> neighborFish;
    private List<Square> neighborEmpty;
    private enum predatorState{
        PREDATOR, PREY, EMPTY
    }

    private predatorState myPredatorState;

    public predatorState getState(){
        return myPredatorState;
    }


    public SquarePredator (List<Square> myNeighbors, int life, int breedingPeriod,
                           predatorState state) {
        super(myNeighbors);
        myLife = life;
        myBreedingPeriod = breedingPeriod;
        myPredatorState = state;
        // TODO Auto-generated constructor stub 
    }
    /**
     * Updates myNeighbors with the current state of the neighbors of the cell in the grid
     * @param neighbors
     */
    public void updateNeighbors(List<Square> neighbors){
        myNeighbors = neighbors;
        //Case statements classifying neighbors into neighborFish and neighborEmpty go here
    }

    /**
     *  Updates cell according to rules of Wa-Tor simulation. States of nearby cells
     *  are given by neighborFish and neighborEmpty
     */
    public void updateSquare(){
        
    }



}
