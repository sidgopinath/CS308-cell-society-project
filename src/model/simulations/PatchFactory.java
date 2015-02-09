package model.simulations;

import model.patches.Patch;
import model.patches.SugarPatch;

public class PatchFactory {
    
    public Patch getPatch(String type, int x, int y){
        if(type.equals("Sugar")){
            return new SugarPatch(x,y);
        } else{
            return new Patch(x,y);
        }
        
    }

}
