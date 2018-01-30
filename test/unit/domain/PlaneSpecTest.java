package unit.domain;

import com.mscharhag.oleaster.runner.OleasterRunner;
import domain.entities.Plane;
import domain.entities.exceptions.PlaneInvalidBoundsException;
import org.junit.runner.RunWith;
import utils.Tuple;
import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;


@RunWith(OleasterRunner.class)
public class PlaneSpecTest {
    {

        describe("Planes", () -> {
            it("should create a plane", () -> {

                Plane p = new Plane(new Tuple<>(5, 10));

                expect(p.getBoundX()).toEqual(5);
                expect(p.getBoundY()).toEqual(10);
            });

            it("should't create a horizontal line", () -> {
                expect( () -> {
                    new Plane(new Tuple<>(2, 0));
                }).toThrow(PlaneInvalidBoundsException.class, "0 is not a valid bound for Y axis.");
            });


            it("should't create a vertical line", () -> {
                expect( () -> {
                    new Plane(new Tuple<>(0, 5));
                }).toThrow(PlaneInvalidBoundsException.class, "0 is not a valid bound for X axis.");
            });

            it("should't create nothing", () -> {
                expect( () -> {
                    new Plane(new Tuple<>(0, 0));
                }).toThrow(PlaneInvalidBoundsException.class, "(0, 0) is a invalid plane.");
            });

            it("should't create a plane with  negatives bounds", () -> {
                expect( () -> {
                    new Plane(new Tuple<>(-1, -1));
                }).toThrow(PlaneInvalidBoundsException.class, "(-1, -1) is a invalid plane.");

            });

        });

    }
}
