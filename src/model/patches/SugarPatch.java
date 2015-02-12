package model.patches;

import javafx.scene.paint.Color;


public class SugarPatch extends Patch {

    private int sugarGrowBackRate;
    private int sugarGrowBackInterval;
    private int currentInterval;
    
    public SugarPatch (int x, int y) {
        super(x, y);
        currentInterval =0;
        sugarGrowBackRate = myPatchPropertyMap.get("sugarGrowBackRate").intValue();
        sugarGrowBackInterval = myPatchPropertyMap.get("sugarGrowBackInterval").intValue();
    }
    

    @Override
    public void update(){
        currentInterval++;
        if(currentInterval == sugarGrowBackInterval){
            currentInterval = 0;
            myPatchPropertyMap.put("sugar", myPatchPropertyMap.get("sugar"));
            if(myPatchPropertyMap.get("sugar") > myPatchPropertyMap.get("maxSugar")){
                myPatchPropertyMap.put("sugar", myPatchPropertyMap.get("maxSugar"));
            }
        }
    }
    @Override
    public Color getColor(){
        if(myPatchPropertyMap.get("sugar") == 0){
            return Color.LIGHTYELLOW;
        } else{
            return Color.ORANGE;
        }
    }
}
