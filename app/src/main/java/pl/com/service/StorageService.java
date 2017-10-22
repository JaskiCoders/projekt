package pl.com.service;

import org.springframework.web.multipart.MultipartFile;
import pl.com.model.storage.File;

import java.util.List;

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
