package services;

import domain.api.SounderDeployAPI;
import domain.entities.Plane;
import domain.entities.Sounder;
import java.util.List;
import java.util.stream.Collectors;

public class SounderService {

    public List<String> deploy(SounderDeployAPI deploy) {

        Plane plane = deploy.plane.toEntity();

        return deploy.sounders.stream().map( sa -> {
            try {
                Sounder s = sa.toEntity(plane);
                sa.getActions().stream().forEach(s::act);
                return s.getInfo();
            }catch (Exception e) {
                return e.getMessage();
            }
        }).collect(Collectors.toList());

    }




}
