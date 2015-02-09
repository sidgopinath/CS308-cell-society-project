package model.simulations;

import model.cells.Cell;
import model.cells.AgentCellEmpty;
import model.cells.AgentCellO;
import model.cells.AgentCellX;

public class AgentCellFactory extends AbstractCellFactory{
    private double mySatisfaction;
    
	public AgentCellFactory(double satisfaction){
        mySatisfaction = satisfaction;
    }
    @Override
    public Cell getCell (int type) {
        if(type ==0){
            return new AgentCellEmpty(mySatisfaction);
        } else if(type == 1){
            return new AgentCellO(mySatisfaction);
        } else if(type == 2){
            return new AgentCellX(mySatisfaction);
        } else{
        	return gridInputError();
        }
    }

}
