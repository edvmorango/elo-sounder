package domain.entities;

import domain.entities.exceptions.PlaneInvalidBoundsException;
import utils.Tuple;

public class Plane {

    private Tuple<Integer, Integer> bounds;

    public Plane(Tuple<Integer, Integer> bounds) throws PlaneInvalidBoundsException {

        if(bounds.x <= 0  && bounds.y <= 0)
            throw new PlaneInvalidBoundsException("("+bounds.x+", "+bounds.y+") is a invalid plane.");
        else if(bounds.x <= 0)
            throw new PlaneInvalidBoundsException(bounds.x +" is not a valid bound for X axis.");
        else if(bounds.y <= 0)
            throw new PlaneInvalidBoundsException(bounds.y +" is not a valid bound for Y axis.");

        this.bounds = bounds;

    }

    public String toString() {
        return "(" + bounds.x + ", " +  bounds.y +")";
    }

    public Integer getBoundX() {
        return this.bounds.x;
    }

    public Integer getBoundY() {
        return this.bounds.y;
    }

}
