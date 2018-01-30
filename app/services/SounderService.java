package services;

import com.google.inject.ImplementedBy;
import domain.api.SounderDeployAPI;

import java.util.List;

@ImplementedBy(SounderServiceImpl.class)
public interface SounderService {

    List<String> deploy(SounderDeployAPI deploy);

}
