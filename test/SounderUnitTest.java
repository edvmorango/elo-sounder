import akka.actor.ActorSystem;
import domain.entities.Plane;
import domain.entities.Sounder;
import org.junit.Test;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;
import utils.Tuple;

import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class SounderUnitTest {

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

        assertThat(s.toString()).isEqualTo("1 3 N");
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

        assertThat(s.toString()).isEqualTo("5 1 E");
    }


}

