package model.simulations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;
import model.cells.PredatorCellEmpty;
import model.cells.PredatorCellFish;
import model.cells.PredatorCellShark;

public class PredatorCellFactory extends AbstractCellFactory {
    private int myBreedingPeriod;
    private int mySharkLife;

    public PredatorCellFactory(int breedingPeriod, int sharkLife){
        myBreedingPeriod = breedingPeriod;
        mySharkLife = sharkLife;
    }
    
    @Override
    public Cell getCell (int type) {
        if(type == 0){
            return new PredatorCellEmpty(-1);
        } else if(type == 1){
            return new PredatorCellFish(myBreedingPeriod);
        } else if(type == 2){
            return new PredatorCellShark(myBreedingPeriod, mySharkLife);
        } else{
        	throw new ValueException("Error with grid input values");
        }
    }

}
