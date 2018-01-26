package generators;

import domain.entities.Plane;
import org.quicktheories.core.Gen;

public class PlaneGen  {

    static Gen<Plane> planes(){
        return UtilsGen.points().map(Plane::new);
    }


}
