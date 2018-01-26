package property;

import domain.entities.Plane;
import domain.entities.Sounder;
import generators.SounderGen;
import org.junit.Test;
import org.quicktheories.WithQuickTheories;
import utils.Tuple;

import java.util.stream.IntStream;




public class SounderPropertyTests implements WithQuickTheories {

    private SounderGen gen;

    public SounderPropertyTests() {
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

                    IntStream.rangeClosed(1, p.getBoundX() - c.a)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));

                    return  s.getCoordinate().a.equals(p.getBoundX());

                case W:

                    IntStream.rangeClosed(1, c.a)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));
                    return s.getCoordinate().a.equals(0);
                case N:

                    IntStream.rangeClosed(1, p.getBoundY() - c.b)
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));

                    return s.getCoordinate().b.equals(p.getBoundY());
                case S:

                    IntStream.rangeClosed(1, c.b )
                            .forEach( (i) -> s.act(Sounder.Action.MOVE));
                    return s.getCoordinate().b.equals(0);
                default:
                    return false;
            }


        });

    }

}

