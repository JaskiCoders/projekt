package pl.com.service;

import pl.com.model.Estimation;

import java.util.List;

public interface EstimationService {

    List<Estimation> findAllEstimation();

    Estimation findEstimationById(Long id);

    Estimation addEstimation(Estimation estimation);

    Estimation updateEstimation(Estimation estimation);

    void deleteEstimation(Estimation estimation);

    void deleteEstimationById(Long id);
}
