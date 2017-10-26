package pl.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.com.AppApplication;
import pl.com.service.FileService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
@TestPropertySource(locations="classpath:application.properties")
@AutoConfigureMockMvc
public class StorageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FileService fileService;

    @Before
    public void init() {

    }

    @Test
    public void getFiles() throws Exception {
    }

    @Test
    @Rollback
    public void shouldSaveUploadedFile() throws Exception {
        String name = "file";
        String originalFilename = "test.txt";
        MockMultipartFile multipartFile = new MockMultipartFile(name, originalFilename,
                "text/plain", "Spring Framework".getBytes());

        this.mvc.perform(fileUpload("/api/file").file(multipartFile).param("fileName", name))
                .andExpect(status().isOk());
    }

}