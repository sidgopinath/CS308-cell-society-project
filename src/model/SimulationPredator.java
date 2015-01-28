package model;

import java.util.Map;

public class SimulationPredator extends Simulation {
    
    Square grid[][];
    private enum predatorState{
        PREDATOR, PREY, EMPTY
    }
    
    public SimulationPredator(Map<String,String> paramMap){
        runSim(paramMap);
    }
    
    /*
     * Initializes sim, parses through data passed into upon initialization
     */
    public void runSim(Map<String,String> paramMap){
        
    }
    
    /*
     * Initialize grid
     */
    public void fillGrid(){
        
    }
    
    /*
     * Update grid at step and updates corresponding view
     */
    public void updateGrid(){
        
    }
    /*
     * Method to be run in updateGrid that passes in a copy of the current state
     * of a cells neighbor and updates it's myNeighbors accordingly.
     */
    public void updateNeighbors(){
        
    }
    
}

