package model.cells;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;

public abstract class Cell {
    protected List<Cell> myNeighbors;
    protected Map<String,Double> myPropertyMap;
    protected int myX;
    protected int myY;
    
    public abstract Color getColor();
    public void setNeighbors(List<Cell> neighbors){
        myNeighbors = neighbors;
    }
    
    public Cell(){
        myPropertyMap = new HashMap<String,Double>();
    }
    /*
     * Return clone of map with properties, to protect myPropertyMap from modification
     */
    public Map<String,Double> viewProperties(){
        Map<String,Double> clonedMap = new HashMap<String,Double>();
        for(String property: myPropertyMap.keySet()){
            clonedMap.put(property, myPropertyMap.get(property));
        }
        return clonedMap;
    }
    /*
     * Returns new cell 
     */
//    public abstract Cell move();
    /*
     * Returns new Cell representing state of cell at current position
     */
    public abstract Cell update();
    
    
}
