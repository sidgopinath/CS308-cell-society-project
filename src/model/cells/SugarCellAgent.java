package model.cells;

import javafx.scene.paint.Color;

public class SugarCellAgent extends SugarCell {
    
    public static final String myVision = "myVision";
    public static final String myMetabolism = "myMetabolism";
    public static final String mySugar = "mySugar";
    
    @Override
    public Color getColor(){
        return Color.RED;
    }

    public SugarCellAgent(int vision,int metabolism, int initSugar){
        myPropertyMap.put(myVision, (double) vision);
        myPropertyMap.put(myMetabolism, (double) metabolism);
        myPropertyMap.put(mySugar, (double) initSugar);
    }
    
    public Cell update(){
        
        
        //Implement a move function that picks out patch with highest sugar
        //here. Move this cell and return this cell
        
        myPropertyMap.put(mySugar, myPropertyMap.get(mySugar) 
                          - myPropertyMap.get(myMetabolism));
        
        return this;
    }
}
