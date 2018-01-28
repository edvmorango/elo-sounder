package domain.entities;

import exceptions.SounderExceptions.SounderInvalidActionException;
import exceptions.SounderExceptions.SounderInvalidDirectionException;
import exceptions.SounderExceptions.SounderInvalidStartPointException;
import exceptions.SounderExceptions.SounderMoveOutOfBoundsException;
import play.Logger;
import utils.Tuple;

public class Sounder {

    private Plane plane;
    private Direction direction;
    private Tuple<Integer, Integer> coordinate;

    public Sounder(Plane plane, Tuple<Integer, Integer> coordinate, Direction direction) throws SounderInvalidStartPointException{

        if(coordinate.x > plane.getBoundX() || coordinate.y > plane.getBoundY())
            throw new SounderInvalidStartPointException("SounderInvalidStartPointException: "+coordinate+" is above of plane bounds");
        else if(coordinate.x < 0 || coordinate.y < 0)
            throw new SounderInvalidStartPointException("SounderInvalidStartPointException: "+coordinate+" is under of plane bounds ");

        this.plane = plane;
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public String toString() {
        return
                "Coordinate (" + this.coordinate.x
                        + "," + this.coordinate.y
                        + ") Direction " + this.direction;
    }

    public void act(Action ac)  {
        switch (ac) {
            case MOVE:
                move();
                break;
            case LEFT:
                this.direction = this.direction.rotateLeft();
                break;
            case RIGHT:
                this.direction = this.direction.rotateRight();
                break;
        }
    }

    private void move() throws SounderMoveOutOfBoundsException {
        switch (this.direction) {
            case N:
                moveY(1);
                break;
            case E:
                moveX(1);
                break;
            case S:
                moveY(-1);
                break;
            case W:
                moveX(-1);
                break;
        }
    }

    private void moveY(Integer val) throws SounderMoveOutOfBoundsException {
        Integer newY = this.coordinate.y + val;

        if(newY < 0 || newY > this.plane.getBoundY())
            throw new SounderMoveOutOfBoundsException("Invalid move, last contact was in: "+this.toString());
        else
            this.coordinate = new Tuple<>(this.coordinate.x, newY);
    }

    private void moveX(Integer val) throws SounderMoveOutOfBoundsException {
        Integer newX = this.coordinate.x + val;

        if(newX < 0 || newX > this.plane.getBoundX())
            throw new SounderMoveOutOfBoundsException("Invalid move, last contact was in: "+this.toString());
        else
            this.coordinate = new Tuple<>(newX, this.coordinate.y);
    }

    public enum Action {
        MOVE('M'), LEFT('L'), RIGHT('R');

        private char rawValue;

        Action(char value) {
            this.rawValue = value;
        }

        public static Action getByRawValue(char value) throws SounderInvalidActionException {
            switch (value) {
                case 'M': return MOVE;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default: throw new SounderInvalidActionException("Invalid action "+value);
            }
        }

    }

    public enum Direction {
        N(0), E(1), S(2), W(3);

        private int rawValue;

        Direction(int value){
            this.rawValue = value;
        }

        private Direction getByIntValue(int value) {
            switch (value) {
                case 0: return N;
                case 1: return E;
                case 2: return S;
                default: return W;
            }
        }

        public static Direction getByCharValue(char value){
            switch (value) {
                case 'N' : return N;
                case 'E' : return E;
                case 'S' : return S;
                case 'W' : return W;
                default: throw new SounderInvalidDirectionException("Invalid direction for char: "+value);
            }
        }

        public Direction rotateLeft() {
            if (this.rawValue == 0)
                return getByIntValue(3);
            else
                return getByIntValue(this.rawValue - 1);
        }

        public Direction rotateRight() {
            try {
                if (this.rawValue == 3)
                    return getByIntValue(0);
                else
                    return getByIntValue(this.rawValue + 1);
            } catch (SounderInvalidDirectionException e){
                Logger.error("Could't rotate right \n"+e.getMessage());
                return this;
            }
        }

    }

    public Plane getPlane() {
        return plane;
    }

    public Tuple<Integer, Integer> getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() { return direction; }

    public String getInfo() {
        return this.coordinate.x
                + " " + this.coordinate.y
                + " " + this.direction;
    }
}