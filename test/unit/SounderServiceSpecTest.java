package unit;


import com.mscharhag.oleaster.runner.OleasterRunner;
import domain.entities.exceptions.PlaneInvalidBoundsException;
import fixture.SounderDeployAPIFixture;
import org.junit.runner.RunWith;
import services.SounderServiceImpl;

import java.util.List;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;


@RunWith(OleasterRunner.class)
public class SounderServiceSpecTest {
    SounderServiceImpl sounderService = new SounderServiceImpl();

    {

        describe("Sounder Service", () -> {
            it("should deploy sounders into plane ", () -> {

               List<String> deployLog = sounderService.deploy(SounderDeployAPIFixture.getDefault());
               expect(deployLog).toContain("1 3 N");
               expect(deployLog).toContain("5 1 E");

            });

            it("should't deploy sounders into invalid plane", () -> {

                expect(() -> {
                    sounderService.deploy(SounderDeployAPIFixture.getInvalidPlane());
                }).toThrow(PlaneInvalidBoundsException.class, "0 is not a valid bound for X axis.");

            });

            it("should deploy valid/invalid sounders into plane", () -> {

                List<String> deployLog = sounderService.deploy(SounderDeployAPIFixture.getMixedSounders());
                expect(deployLog).toContain("1 3 N");
                expect(deployLog).toContain("5 1 E");
                expect(deployLog).toContain("(-1,2) is outer of plane bounds");
                expect(deployLog).toContain("Invalid move, last contact was in: Coordinate (1,5) Direction N");

            });
        });


    }
}