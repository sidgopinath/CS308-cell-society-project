package model;

import java.util.List;

public abstract class Square {

    List<Square> myNeighbors;
    
    public Square(List<Square> neighbors){
        myNeighbors = neighbors;
    }
}
