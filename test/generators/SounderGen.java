package generators;

import domain.entities.Sounder;
import org.quicktheories.core.Gen;
import utils.Tuple;

import static org.quicktheories.generators.SourceDSL.integers;

public class SounderGen {

    private PlaneGen planesGen;
    private UtilsGen utilsGen;

    public SounderGen() {
        this.planesGen = new PlaneGen();
        this.utilsGen = new UtilsGen();
    }


    public Gen<Sounder> sounders(){

     return   planesGen.planes().zip(utilsGen.points(), Tuple::new)
                .assuming( t -> t.a.getBoundX() >= t.b.a && t.a.getBoundY() >= t.b.b)
                .zip(directions(), (t,ac) -> new Sounder(t.a,t.b,ac) );

    }

    public Gen<Sounder.Direction> directions() {
       return integers().between(0,Sounder.Direction.values().length - 1).map( v -> Sounder.Direction.values()[v]);
    }

    public Gen<Sounder.Action> actions() {
        return integers().between(0,Sounder.Action.values().length - 1).map( v -> Sounder.Action.values()[v]);
    }

}
