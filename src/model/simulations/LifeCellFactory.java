package model.simulations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;
import model.cells.LifeCellDead;
import model.cells.LifeCellAlive;

public class LifeCellFactory extends AbstractCellFactory {

    @Override
    public Cell getCell (int type) {
        if(type == 0){
            return new LifeCellDead();
        }else if(type == 1){
            return new LifeCellAlive();
        }else{
        	throw new ValueException("Error with grid input values");
        }
    }

}
