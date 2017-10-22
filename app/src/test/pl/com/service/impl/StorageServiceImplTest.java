package pl.com.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.AppApplication;
import pl.com.TestConfiguration;
import pl.com.configuration.properties.StorageProperties;
import pl.com.model.File;
import pl.com.repository.FileRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class StorageServiceImplTest {

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    FileRepository fileRepository;

    @Test
    @Rollback
    public void store() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());

        storageService.store(mockMultipartFile, "name");

        //when(fileRepository.count()).thenReturn(1L);

        File file = fileRepository.findOne(1L);

        assertThat(file.getFileName()).isEqualTo("name");
    }
}