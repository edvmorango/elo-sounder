package domain.entities;

import exceptions.SounderInvalidDirectionException;
import play.Logger;
import utils.Tuple;

public class Sounder {

    public enum Action {RIGHT, LEFT, MOVE}

    public enum Direction {
        N(0), E(1), S(2), W(3);

        private int value;

         Direction(int value){
            this.value = value;
        }

        private Direction getByValue(int value) throws SounderInvalidDirectionException{
             switch (value) {
                 case 0: return N;
                 case 1: return E;
                 case 2: return S;
                 case 3: return W;
                 default: throw new SounderInvalidDirectionException("Invalid direction");
             }
        }

        public Direction rotateLeft(){
           try {
               if (this.value == 0)
                   return getByValue(3);
               else
                   return getByValue(this.value - 1);
           } catch (SounderInvalidDirectionException e){
               Logger.error("Could't rotate left \n"+e.getMessage());
               return this;
            }
        }

        public Direction rotateRight() {
           try {
               if (this.value == 3)
                   return getByValue(0);
               else
                   return getByValue(this.value + 1);
           } catch (SounderInvalidDirectionException e){
               Logger.error("Could't rotate right \n"+e.getMessage());
               return this;
           }
        }

    }

    private Plane plane;
    private Direction direction;
    private Tuple<Integer, Integer> coordinate;

    public Sounder(Plane plane, Tuple<Integer, Integer> coordinate, Direction direction) {
        this.plane = plane;
        this.direction = direction;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return this.coordinate.a
                        + " " + this.coordinate.b
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

    private void move() {
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

    private void moveY(Integer val) {
        Integer newY = this.coordinate.b + val;
        this.coordinate = new Tuple<Integer, Integer>(this.coordinate.a, newY);
    }

    private void moveX(Integer val) {
        Integer newX = this.coordinate.a + val;
        this.coordinate = new Tuple<Integer, Integer>(newX, this.coordinate.b);
    }


}