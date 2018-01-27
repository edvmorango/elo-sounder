package domain.api;

import utils.Tuple;

public class CoordinateAPI {

    public Integer x;
    public Integer y;

    public Tuple<Integer, Integer> toTuple() {
        return new Tuple<>(x,y);
    }


}
