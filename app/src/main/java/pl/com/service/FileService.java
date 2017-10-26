package pl.com.service;

import org.springframework.web.multipart.MultipartFile;
import pl.com.model.storage.File;

import java.util.List;

public interface FileService {

    File saveFile(MultipartFile file, String fileName);

    List<File> findAllFiles();

    void deleteFile(String pathToFile);
//    Stream<Path> loadAll();
//
    File load(String pathToFile);
//
//    Resource loadAsResource(String filename);
//
    void deleteAllFiles();
}
