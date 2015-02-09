package model.simulations;

import model.cells.Cell;
import model.cells.LifeCellDead;
import model.cells.LifeCellAlive;

public class LifeCellFactory extends AbstractCellFactory {

    @Override
    public Cell getCell (int type) {
    	System.out.println(type);
        if(type == 0){
            return new LifeCellDead();
        }else if(type == 1){
            return new LifeCellAlive();
        }else{
        	return gridInputError();
        }
    }

}
