package pl.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import pl.com.AppApplication;
import pl.com.service.StorageService;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    private StorageService storageService;

    @Before
    public void init() {

    }

    @Test
    public void getFiles() throws Exception {
    }

    @Test
    @Rollback
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());

        this.mvc.perform(fileUpload("/api/file").file(multipartFile).param("fileName", "file"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("fileName", is("file")));

        //then(this.storageService).should().store(multipartFile, "file");
    }

}