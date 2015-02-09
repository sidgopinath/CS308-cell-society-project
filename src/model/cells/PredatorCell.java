package model.cells;


/**
 * Abstract superclass for squares in the Predator-Prey simulation
 * Contains data and methods unique to Predator-Prey simulation
 * @author Janan
 *
 */

public abstract class PredatorCell extends Cell{

    static final String myBreedingPeriod = "myBreedingPeriod";
    static final String myCurrentBreeding = "myCurrentBreeding";
    static final String isEdible = "isEdible";
    static final String isMovable = "isMovable";


    public PredatorCell (int breedingPeriod) {
        myPropertyMap.put(myBreedingPeriod,(double) breedingPeriod);
        myPropertyMap.put(myCurrentBreeding,(double) breedingPeriod);
    }

    /**
     *  Updates constants of the cell that change with time, like breeding status
     *  and starvation status 
     */
    public abstract Cell update();

    /*
     * Returns square that this square wants to move to, unless all squares
     * are full, in which case it returns itself.
     */
    public abstract Cell moveSquareTo();


    /*
     * Public method to allow subclasses to decrement breeding countdown on update
     */
    public void decrementBreeding(){
        double currentBreeding = myPropertyMap.get(myCurrentBreeding);
        if(currentBreeding == 0){
            myPropertyMap.put(myCurrentBreeding, myPropertyMap.get(myBreedingPeriod)-1);
        }else{
            myPropertyMap.put(myCurrentBreeding,currentBreeding-1);
        }
    }

    /*
     * Indicate whether a square has reached its breeding period
     */
    /*
     * Returns a new square that should be placed in the grid as a result of breeding
     */
}


