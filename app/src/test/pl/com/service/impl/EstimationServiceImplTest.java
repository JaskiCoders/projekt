package pl.com.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.model.Estimation;
import pl.com.model.storage.Folder;
import pl.com.repository.EstimationRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class EstimationServiceImplTest {

    @InjectMocks
    private EstimationServiceImpl estimationService;

    @Mock
    EstimationRepository estimationRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllEstimation() throws Exception {

    }

    @Test
    public void findEstimationById() throws Exception {
    }

    @Test
    public void addEstimation() throws Exception {
        String title = "Test1";
        Estimation estimation = createEstimation(title);
        Long estimationId = estimation.getId();
        when(estimationRepository.getOne(estimationId)).thenReturn(estimation);
        when(estimationRepository.getOne(estimationId).getTitle()).thenReturn(title);
    }

    @Test
    public void updateEstimation() throws Exception {
        String title = "Test1", title2 = "Test2";
        Estimation estimation = createEstimation(title);
        Long estimationId = estimation.getId();
        when(estimationRepository.getOne(estimationId)).thenReturn(estimation);
        when(estimationRepository.getOne(estimationId).getTitle()).thenReturn(title);
        estimation.setTitle(title2);
        when(estimationRepository.getOne(estimationId)).thenReturn(estimation);
        when(estimationRepository.getOne(estimationId).getTitle()).thenReturn(title2);
    }

    @Test
    public void deleteEstimation() throws Exception {
    }

    @Test
    public void deleteEstimationById() throws Exception {
    }

    private Estimation createEstimation(String title) {
        Estimation estimation = new Estimation();
        estimation.setTitle(title);
        estimation.setRepository(new Folder());
        return estimation;
    }
}