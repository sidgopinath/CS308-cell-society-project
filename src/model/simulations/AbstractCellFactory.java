package model.simulations;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;

public abstract class AbstractCellFactory {
    public abstract Cell getCell(int type) throws ValueException;
}
