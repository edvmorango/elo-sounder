package property;

import domain.entities.Plane;
import domain.entities.Sounder;
import generators.SounderGen;
import org.junit.Test;
import org.quicktheories.WithQuickTheories;
import utils.Tuple;

import java.util.stream.IntStream;




public class SounderPropertySpecTest implements WithQuickTheories {

    private SounderGen gen;

    public SounderPropertySpecTest() {
        gen = new SounderGen();
    }


    @Test
    public void moveToBound() {

        qt().withFixedSeed(0).withGenerateAttempts(25).forAll(gen.sounders()).check( (s) -> {

            Plane p = s.getPlane();
            Tuple<Integer, Integer> c = s.getCoordinate();
            Sounder.Direction d = s.getDirection();

            switch (d) {
                case E:

                    IntStream.rangeClosed(1, p.getBoundX() - c.x)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));

                    return  s.getCoordinate().x.equals(p.getBoundX());

                case W:

                    IntStream.rangeClosed(1, c.x)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));
                    return s.getCoordinate().x.equals(0);
                case N:

                    IntStream.rangeClosed(1, p.getBoundY() - c.y)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));

                    return s.getCoordinate().y.equals(p.getBoundY());
                case S:

                    IntStream.rangeClosed(1, c.y)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));
                    return s.getCoordinate().y.equals(0);
                default:
                    return false;
            }


        });

    }

}

