package unit.domain;

import com.google.inject.Inject;
import com.mscharhag.oleaster.runner.OleasterRunner;
import domain.entities.Plane;
import domain.entities.Sounder;
import domain.entities.exceptions.PlaneInvalidBoundsException;
import domain.entities.exceptions.SounderInvalidStartPointException;
import domain.entities.exceptions.SounderMoveOutOfBoundsException;
import org.junit.runner.RunWith;
import services.SounderService;
import utils.Tuple;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;


@RunWith(OleasterRunner.class)
public class SounderSpecTest {
    {

        describe("Sounders ", () -> {
            Plane plane = new Plane(new Tuple<>(5,5));


            it("should deploy sounder (5,5) (1,2,N) and move to (1,3,N)", () -> {

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

                expect(s.getInfo()).toMatch("1 3 N");
            });

            it("should deploy sounder (5,5) (3,3,E) and move to (5,1,E)", () -> {

                Sounder s = new Sounder(plane,  new Tuple<>(3,3), Sounder.Direction.E);

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

                expect(s.getInfo()).toMatch("5 1 E");
            });

            it("should't deploy above bound X", () -> {
                expect( () -> {
                    new Sounder(plane, new Tuple<>(7,2), Sounder.Direction.N);
                }).toThrow(SounderInvalidStartPointException.class, "(7,2) is outer of plane bounds");
            });

            it("should't deploy under 0", () -> {
                expect( () -> {
                    new Sounder(plane, new Tuple<>(-1,2), Sounder.Direction.N);
                }).toThrow(SounderInvalidStartPointException.class, "(-1,2) is outer of plane bounds");
            });

            it("should't move beyond X", () -> {
                expect( () -> {
                    Sounder s = new Sounder(plane, new Tuple<>(5,5), Sounder.Direction.N);
                    s.act(Sounder.Action.MOVE);
                }).toThrow(SounderMoveOutOfBoundsException.class, "Invalid move, last contact was in: Coordinate (5,5) Direction N");
            });

            it("should't move under 0", () -> {
                expect( () -> {
                    Sounder s = new Sounder(plane, new Tuple<>(0,0), Sounder.Direction.S);
                    s.act(Sounder.Action.MOVE);
                }).toThrow(SounderMoveOutOfBoundsException.class, "Invalid move, last contact was in: Coordinate (0,0) Direction S");
            });

        });

    }


}

