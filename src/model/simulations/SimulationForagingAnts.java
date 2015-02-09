package model.simulations;

import java.util.Map;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import view.SimulationScreen;

public class SimulationForagingAnts extends Simulation{

	public SimulationForagingAnts(Map<String, String> paramMap,
			Integer[][] grid, SimulationScreen simScreen) {
		super(paramMap, paramMap, grid, simScreen);
		// TODO Auto-generated constructor stub
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
	AbstractCellFactory getCellFactory() throws ValueException {
		// TODO Auto-generated method stub
		return null;
	}

}
