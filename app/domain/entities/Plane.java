package domain.entities;

import utils.Tuple;

public class Plane {

    private Tuple<Integer, Integer> bounds;

    public Plane(Tuple bounds){
        this.bounds = bounds;
    }

}
