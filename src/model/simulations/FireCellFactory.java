package model.simulations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;
import model.cells.FireCellBurning;
import model.cells.FireCellEmpty;
import model.cells.FireCellTree;

public class FireCellFactory extends AbstractCellFactory {
    private Integer myProbCatch;
    
    public FireCellFactory(int probCatch){
        
    	myProbCatch = probCatch;
    }
    @Override
    public Cell getCell (int type) {
        if(type == 0){
            return new FireCellEmpty();
        }else if(type ==1){
            return new FireCellTree();
        } else if(type == 2){
            return new FireCellBurning(myProbCatch);
        } else{
        	throw new ValueException("Error with grid input values");
        }
    }

}
