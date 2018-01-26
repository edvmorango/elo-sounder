package generators;

import domain.entities.Plane;
import org.quicktheories.core.Gen;
import utils.Tuple;
import static org.quicktheories.generators.SourceDSL.integers;

public class PlaneGen  {

    static Gen<Plane> planes(){
        return UtilsGen.point().map(Plane::new);
    }


}
