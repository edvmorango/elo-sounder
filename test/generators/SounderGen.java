package generators;

import domain.entities.Plane;
import domain.entities.Sounder;
import org.quicktheories.core.Gen;
import org.quicktheories.impl.ConcreteDetachedSource;
import utils.Tuple;

import static org.quicktheories.generators.SourceDSL.integers;

public class SounderGen {

    static Gen<Sounder> sounders(){

     return   PlaneGen.planes().zip(UtilsGen.points(), Tuple::new)
                .assuming( t -> t.a.getBoundX() <= t.b.a && t.a.getBoundY() <= t.b.b)
                .zip(directions(), (t,ac) -> new Sounder(t.a,t.b,ac) );
    }

    static Gen<Sounder.Direction> directions() {
       return integers().between(0,Sounder.Direction.values().length).map( v -> Sounder.Direction.values()[v]);
    }

    static Gen<Sounder.Action> actions() {
        return integers().between(0,Sounder.Action.values().length).map( v -> Sounder.Action.values()[v]);
    }

}
