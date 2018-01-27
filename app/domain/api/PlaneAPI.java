package domain.api;

import domain.entities.Plane;
import utils.Tuple;

public class PlaneAPI {

    public  Integer boundX;
    public  Integer boundY;

    public Plane toEntity() {
        Tuple<Integer, Integer> bounds = new Tuple<>(boundX, boundY);
        return new Plane(bounds);
    }

}
