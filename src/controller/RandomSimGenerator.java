package controller;

import java.util.HashMap;
import java.util.Map;

public class RandomSimGenerator {

	private Integer[][] myGrid;
	private HashMap<String, String> myParameters;
	
	public RandomSimGenerator(int gridWidth, int gridHeight, String simName, Map<String, Integer> fillProbability){
		myParameters.put("simName", simName);
		myParameters.put("gridWidth", gridWidth + "");
		myParameters.put("gridHeight", gridHeight + "");
		myGrid = new Integer[gridHeight][gridWidth];
		
		if(fullyRandom){
			createRandomGrid(myGrid);
		}
		else{
			createProbabilityGrid(myGrid, fillProbability);
		}
	}
	
}
