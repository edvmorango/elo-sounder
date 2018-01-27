package services;

import domain.api.SounderAPI;
import domain.api.SounderDeployAPI;
import domain.entities.Plane;
import domain.entities.Sounder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SounderService {

    public List<String> deploy(SounderDeployAPI deploy) {

        Plane plane = deploy.plane.toEntity();

        return deploy.sounders.stream().map( sa -> {
            Sounder s = sa.toEntity(plane);
            sa.getActions().stream().forEach(s::act);
            return s.getInfo();
        }).collect(Collectors.toList());

    }




}
