package model;

import java.util.Map;

import javafx.scene.Scene;

public abstract class Simulation {
		
		/**
		 * first fill grid with appropriate square types
		 * then pass squares their appropriate neighbors
		 * Then update squares
		 */
		public abstract void runSim(Map<String, String> paramMap);
		
		/**
		 * fill grid with squares that have the right values given the parameters and the type of each space
		 * initialize grid, and fill
		 * @param paramMap 
		 */
		abstract void fillGrid(Map<String, String> paramMap);
		
		/**
		 * first it will pass each square its neighbors
		 *  then it will go through and call each cell to update.
		 *  update view
		 */
		abstract void updateGrid();
		
		/**
		 * pass each square its neighbors
		 */
		abstract void updateNeighbors();
		
	}

	
	

