package model.simulations;

import model.cells.Cell;
import model.cells.LifeCellDead;
import model.cells.LifeCellAlive;

public class LifeCellFactory extends AbstractCellFactory {

    @Override
    public Cell getCell (int type) {
        if(type == 0){
            return new LifeCellDead();
        }else{
            return new LifeCellAlive();
        }
    }

}
