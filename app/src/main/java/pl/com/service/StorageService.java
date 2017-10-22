package pl.com.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pl.com.model.File;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

//    void init();

    File store(MultipartFile file, String fileName);

    List<File> findAllFiles();
//    Stream<Path> loadAll();
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll();

}
