package model.simulations;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;

public abstract class AbstractCellFactory {
    public abstract Cell getCell(int type) throws ValueException;


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
	
	protected void paramInputError(){
		System.out.println("Error with parameter input values.");
		System.exit(0);
	}
		
}
