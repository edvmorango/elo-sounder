package unit;

import domain.entities.Plane;
import domain.entities.Sounder;
import exceptions.SounderExceptions.SounderInvalidStartPointException;
import exceptions.SounderExceptions.SounderMoveOutOfBoundsException;
import org.junit.Test;
import utils.Tuple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class SounderTests {

    private Plane plane = new Plane( new Tuple<>(5,5));

    @Test
    public void firstInput() {

        Sounder s = new Sounder(plane, new Tuple<>(1,2), Sounder.Direction.N);

        s.act(Sounder.Action.LEFT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.LEFT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.LEFT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.LEFT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.MOVE);

        assertThat(s.getInfo()).isEqualTo("1 3 N");
    }

    @Test
    public void secondInput() {

        Sounder s = new Sounder(plane, new Tuple<>(3,3), Sounder.Direction.E);

        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.RIGHT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.RIGHT);
        s.act(Sounder.Action.MOVE);
        s.act(Sounder.Action.RIGHT);
        s.act(Sounder.Action.RIGHT);
        s.act(Sounder.Action.MOVE);

        assertThat(s.getInfo()).isEqualTo("5 1 E");
    }

    @Test(expected = SounderInvalidStartPointException.class)
    public void startAbovePlane() {
        new Sounder(plane, new Tuple<>(7,2), Sounder.Direction.N);
    }

    @Test(expected = SounderInvalidStartPointException.class)
    public void startUnderPlane() {
        new Sounder(plane, new Tuple<>(-1,2), Sounder.Direction.N);
    }

    @Test(expected = SounderMoveOutOfBoundsException.class)
    public void moveAboveUpperBound() {
        Sounder s = new Sounder(plane, new Tuple<>(5,5), Sounder.Direction.N);
        s.act(Sounder.Action.MOVE);

    }

    @Test(expected = SounderMoveOutOfBoundsException.class)
    public void moveUnderBottomBound() {
        Sounder s = new Sounder(plane, new Tuple<>(0,0), Sounder.Direction.S);
        s.act(Sounder.Action.MOVE);

    }


}

