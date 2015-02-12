package model.simulations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;
import model.cells.SugarCellAgent;
import model.cells.SugarCellEmpty;

public class SugarCellFactory extends AbstractCellFactory {
    int myVision;
    int myMetabolism;
    int myInitialSugar;
    
     public SugarCellFactory (int vision, int metabolism, int initialSugar) {
         myVision = vision;
         myMetabolism = metabolism;
         myInitialSugar = initialSugar;
    }
    
    @Override
    public Cell getCell (int type) throws ValueException {
        if(type == 0){
            return new SugarCellEmpty();
        } else if (type == 1){
            return new SugarCellAgent(myVision,myMetabolism,myInitialSugar);
        } else{
            throw new ValueException("Error with grid input values");
        }
    }

}
