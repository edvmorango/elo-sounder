package domain.api;

import domain.entities.Plane;
import domain.entities.Sounder;

import java.util.List;
import java.util.stream.Collectors;

public class SounderAPI {

    public char direction;
    public CoordinateAPI coordinate;
    public String actions;

    public List<Sounder.Action> getActions() {

        return actions.chars().mapToObj(i -> (char) i)
                .map(Sounder.Action::getByRawValue)
                .collect(Collectors.toList());

    }

    public Sounder toEntity(Plane p) {

        return new Sounder(p, this.coordinate.toTuple(), Sounder.Direction.getByCharValue(direction));

    }

}
