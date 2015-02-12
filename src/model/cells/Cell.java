package model.cells;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.patches.Patch;
import javafx.scene.paint.Color;

/**
 * This is the cell superclass
 * All other cells are subclasses of this
 * It holds a hashmap with all of the properties for each cell
 * @author Janan
 *
 */

public abstract class Cell {
    protected List<Cell> myNeighbors;
    protected Map<String,Double> myPropertyMap;
    protected int myX;
    protected int myY;
    protected Patch myPatch;
    
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
    
    public void setCoords(int x, int y){
        myX = x;
        myY = y;
    }
    
    public int getX(){
        return myX;
    }
    
    public int getY(){
        return myY;
    }

    /*
     * Returns new Cell representing state of cell at current position
     */
    public abstract Cell update();
    
    public void setPatch(Patch patch){
        myPatch = patch;
    }
}
