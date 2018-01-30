package fixture;

import domain.api.CoordinateAPI;
import domain.api.PlaneAPI;
import domain.api.SounderAPI;
import domain.api.SounderDeployAPI;
import domain.entities.Sounder;

import java.util.ArrayList;
import java.util.List;

public class SounderDeployAPIFixture {


    public static SounderDeployAPI getDefault() {

        PlaneAPI plane = new PlaneAPI();
        plane.boundX = 5;
        plane.boundY = 5;

        CoordinateAPI c1 = new CoordinateAPI();
        c1.x = 1;
        c1.y = 2;

        SounderAPI s1 = new SounderAPI();
        s1.coordinate = c1;
        s1.direction = 'N';
        s1.actions = "LMLMLMLMM";

        CoordinateAPI c2 = new CoordinateAPI();
        c2.x = 3;
        c2.y = 3;

        SounderAPI s2 = new SounderAPI();
        s2.coordinate = c2;
        s2.direction = 'E';
        s2.actions = "MMRMMRMRRM";

        ArrayList<SounderAPI> sounders = new ArrayList<>();


        sounders.add(s1);
        sounders.add(s2);

        SounderDeployAPI sd = new SounderDeployAPI();
        sd.plane = plane;
        sd.sounders = sounders;

        return sd;

    }

    public static SounderDeployAPI getInvalidPlane() {

        PlaneAPI plane = new PlaneAPI();
        plane.boundX = 0;
        plane.boundY = 5;


        SounderDeployAPI sd = new SounderDeployAPI();
        sd.plane = plane;
        sd.sounders = new ArrayList<>();

        return sd;
    }

    public static SounderDeployAPI getMixedSounders() {

        CoordinateAPI c3 = new CoordinateAPI();
        c3.x = -1;
        c3.y = 2;

        SounderAPI s3 = new SounderAPI();
        s3.coordinate = c3;
        s3.direction = 'N';
        s3.actions = "LMLMLMLMM";

        CoordinateAPI c4 = new CoordinateAPI();
        c4.x = 1;
        c4.y = 2;

        SounderAPI s4 = new SounderAPI();
        s4.coordinate = c4;
        s4.direction = 'N';
        s4.actions = "LMLMLMLMMMMMMMMMMMMM";

        SounderDeployAPI def = getDefault();

        def.sounders.add(s3);
        def.sounders.add(s4);

        return def;
    }

}
