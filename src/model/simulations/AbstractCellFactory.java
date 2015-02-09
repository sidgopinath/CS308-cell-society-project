package model.simulations;
import model.cells.Cell;

public abstract class AbstractCellFactory {
    public abstract Cell getCell(int type);


    /**
     * IMPLEMENT TRY/CATCH
     * Seems like a good spot...
     * @return
     */
	protected Cell gridInputError(){
		System.out.println("Error with grid input values.");
    	System.exit(0);
    	return null;
	}
		
}
