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

    @Override
    public String toString() {
        return this.plane
                        + " " + this.coordinate.x
                        + " " + this.coordinate.y
                        + " " + this.direction;
    }

    public void act(Action ac) {
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

    private void move() throws SounderMoveOutOfBoundsException{
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
            throw new SounderMoveOutOfBoundsException("SounderMoveOutOfBoundsException: "+this.toString()+" going to Y: "+newY);
        else
            this.coordinate = new Tuple<>(this.coordinate.x, newY);
    }

    private void moveX(Integer val) throws SounderMoveOutOfBoundsException {
        Integer newX = this.coordinate.x + val;

        if(newX < 0 || newX > this.plane.getBoundX())
            throw new SounderMoveOutOfBoundsException("SounderMoveOutOfBoundsException: "+this.toString()+" going to X: "+newX);
        else
            this.coordinate = new Tuple<>(newX, this.coordinate.y);
    }

    public enum Action {
        MOVE('M'), LEFT('L'), RIGHT('R');

        private char rawValue;

        Action(char value) {
            this.rawValue = value;
        }

        public Action getByRawValue(char value) throws SounderInvalidActionException {
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

        private Direction getByRawValue(int value) throws SounderInvalidDirectionException{
             switch (value) {
                 case 0: return N;
                 case 1: return E;
                 case 2: return S;
                 case 3: return W;
                 default: throw new SounderInvalidDirectionException("Invalid direction "+value);
             }
        }

        public Direction rotateLeft() {
           try {
               if (this.rawValue == 0)
                   return getByRawValue(3);
               else
                   return getByRawValue(this.rawValue - 1);
           } catch (SounderInvalidDirectionException e){
               Logger.error("Could't rotate left \n"+e.getMessage());
               return this;
            }
        }

        public Direction rotateRight() {
           try {
               if (this.rawValue == 3)
                   return getByRawValue(0);
               else
                   return getByRawValue(this.rawValue + 1);
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