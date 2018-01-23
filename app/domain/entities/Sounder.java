package domain.entities;

import utils.Tuple;

public class Sounder {

    public enum Actions { RIGHT, LEFT, MOVE }

    public enum Direction { N, W, E, S }

    private Plane plane;
    private Direction direction;
    private Tuple<Integer, Integer> coordinate;


    public Sounder(Plane plane, Tuple<Integer, Integer> coordinate, Direction direction ){
        this.plane = plane;
        this.direction = direction;
        this.coordinate = coordinate;
    }

}
