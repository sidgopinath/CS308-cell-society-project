package model;

import java.util.HashMap;

import javafx.scene.Scene;

public abstract class Simulation {
		
		
		/**
		 * first fill grid with appropriate square types
		 * then pass squares their appropriate neighbors
		 * Then update squares
		 */
		abstract void runSim();
		
		/**
		 * fill grid with squares that have the right values given the parameters and the type of each space
		 */
		abstract void fillGrid();
		
		/**
		 * first it will pass each square its neighbors
		 *  then it will go through and call each cell to update.
		 */
		abstract void updateGrid();
		
		/**
		 * pass each square its neighbors
		 */
		abstract void passNeighbors();
		
	}

	
	

