package pl.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.model.Estimation;
import pl.com.repository.EstimationRepository;
import pl.com.service.EstimationService;

import java.util.List;

@Service
public class EstimationServiceImpl implements EstimationService {

    @Autowired
    EstimationRepository estimationRepository;

    @Override
    public List<Estimation> findAllEstimation() {
        return estimationRepository.findAll();
    }

    @Override
    public Estimation findEstimationById(Long id) {
        return estimationRepository.findOne(id);
    }

    @Override
    public Estimation addEstimation(Estimation estimation) {
        return estimationRepository.save(estimation);
    }

    @Override
    public Estimation updateEstimation(Estimation estimation) {
        return estimationRepository.save(estimation);
    }

    @Override
    public void deleteEstimation(Estimation estimation) {
        estimationRepository.delete(estimation);
    }

    @Override
    public void deleteEstimationById(Long id) {
        estimationRepository.delete(id);
    }
}
