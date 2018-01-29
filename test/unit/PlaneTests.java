package unit;

import domain.entities.Plane;
import domain.entities.exceptions.PlaneInvalidBoundsException;
import org.junit.Test;
import utils.Tuple;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaneTests {

    @Test
    public void planeIsValid() {
      Plane p = new Plane(new Tuple<>(5,10));

      assertThat(p.getBoundX()).isEqualTo(5);
      assertThat(p.getBoundY()).isEqualTo(10);
    }

    @Test(expected = PlaneInvalidBoundsException.class)
    public void planeIsHorizontalLine() {
        new Plane(new Tuple<>(2,0));
    }

    @Test(expected = PlaneInvalidBoundsException.class)
    public void planeIsVerticalLine() {
        new Plane(new Tuple<>(0,5));
    }

    @Test(expected = PlaneInvalidBoundsException.class)
    public void planeDontExists() {
        new Plane(new Tuple<>(0,0));
    }

    @Test(expected = PlaneInvalidBoundsException.class)
    public void planeBoundsAreNegative() {
        new Plane(new Tuple<>(-1,10));
    }

}
