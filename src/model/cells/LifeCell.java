package model.cells;

import javafx.scene.paint.Color;

/**
 * This is the square superclass for the life simulation
 * It holds relevant information about the square
 * This includes life status and neighbors
 * @author Sunjeev
 *
 */

public abstract class LifeCell extends Cell{
    protected static final String returnCount = "returnCount";


    public LifeCell update(){
        int liveCount = 0;
        for(Cell neighbor: myNeighbors){
            liveCount += neighbor.viewProperties().get(returnCount);
        }
        return this.checkStatus(liveCount);
    }

    protected abstract LifeCell checkStatus(int alive);

}
