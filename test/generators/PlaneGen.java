package generators;

import domain.entities.Plane;
import org.quicktheories.core.Gen;

public class PlaneGen  {

    private UtilsGen utilsGen;

    public PlaneGen() {
        this.utilsGen = new UtilsGen();
    }

    public Gen<Plane> planes(){
        return utilsGen.points().assuming( t -> t.a > 0 && t.b > 0 ).map(Plane::new);
    }


}
