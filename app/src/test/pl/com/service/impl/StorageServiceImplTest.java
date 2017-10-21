package pl.com.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
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
import pl.com.configuration.properties.StorageProperties;
import pl.com.repository.FileRepository;

@ContextConfiguration
@TestPropertySource(properties = { "storage.root = test" })
public class StorageServiceImplTest {

    @InjectMocks
    private StorageServiceImpl storageService;
    @Mock
    private FileRepository fileRepository;
    @Mock
    private StorageProperties storageProperties;

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Rollback
    public void store() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
        storageService.store(mockMultipartFile, "name");
    }
}