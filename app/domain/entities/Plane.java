package domain.entities;

import exceptions.PlaneExceptions.PlaneInvalidBoundsException;
import utils.Tuple;

public class Plane {

    private Tuple<Integer, Integer> bounds;

    public Plane(Tuple<Integer, Integer> bounds) throws PlaneInvalidBoundsException {

        if(bounds.a <= 0  && bounds.b <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds+" is not a plane");
        else if(bounds.a <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds.a+" is not a valid bound for X axis");
        else if(bounds.b <= 0)
            throw new PlaneInvalidBoundsException("PlaneInvalidBoundsException: "+bounds.b+" is not a valid bound for Y axis");

        this.bounds = bounds;

    }

    @Override
    public String toString() {
        return "(" + bounds.a + " " +  bounds.b +")";
    }

    public Integer getBoundX() {
        return this.bounds.a;
    }

    public Integer getBoundY() {
        return this.bounds.b;
    }

}
