package generators;

import org.quicktheories.core.Gen;
import utils.Tuple;

import static org.quicktheories.generators.SourceDSL.integers;

public class UtilsGen {


    static Gen<Tuple<Integer, Integer>> points(){
        Gen<Integer> ints = integers().between(1, 1000);
        return ints.zip(ints, Tuple::new);
    }
}
