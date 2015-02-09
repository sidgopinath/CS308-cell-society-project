package model.simulations;

import java.util.Map;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import view.SimulationScreen;

public class SimulationSugarscape extends Simulation {

	public SimulationSugarscape(Map<String, String> paramMap, Map<String,String> styleMap,
	                            Integer[][] grid,
			SimulationScreen simScreen) {
		super(paramMap, styleMap, grid, simScreen);
	}

	@Override
	void parseMap(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateGrid() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateColorGrid() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public String patchType(){
	    return "Sugar";
	}

    @Override
    AbstractCellFactory getCellFactory () throws ValueException {
        // TODO Auto-generated method stub
        return null;
    }

}
