package pl.com.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.AppApplication;
import pl.com.configuration.properties.StorageProperties;
import pl.com.model.storage.File;
import pl.com.repository.FileRepository;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class FileServiceImplTest {

    @Autowired
    StorageProperties storageProperties;
    @Autowired
    private FileServiceImpl storageService;

    @Autowired
    FileRepository fileRepository;

    @Test
    @Rollback
    public void store() {
        String name = "file";
        String storedFileame = "file";
        String originalFilename = "test.txt";
        MockMultipartFile mockMultipartFile = new MockMultipartFile(name, originalFilename, MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());

        storageService.saveFile(mockMultipartFile, storedFileame);

        File file = fileRepository.findOne(storageProperties.getRoot() + "\\" + originalFilename);

        assertThat(file.getFileName()).isEqualTo(storedFileame);
    }
}