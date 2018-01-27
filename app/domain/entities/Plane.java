package domain.entities;

import exceptions.PlaneExceptions.PlaneInvalidBoundsException;
import utils.Tuple;

public class Plane {

    private Tuple<Integer, Integer> bounds;

    public Plane(Tuple<Integer, Integer> bounds) throws PlaneInvalidBoundsException {

        if(bounds.x <= 0  && bounds.y <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds+" is not x plane");
        else if(bounds.x <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds.x +" is not x valid bound for X axis");
        else if(bounds.y <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds.y +" is not x valid bound for Y axis");

        this.bounds = bounds;

    }

    @Override
    public String toString() {
        return "(" + bounds.x + " " +  bounds.y +")";
    }

    public Integer getBoundX() {
        return this.bounds.x;
    }

    public Integer getBoundY() {
        return this.bounds.y;
    }

}
