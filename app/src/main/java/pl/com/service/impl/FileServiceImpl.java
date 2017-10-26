package pl.com.service.impl;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.com.exception.StorageException;
import pl.com.model.storage.File;
import pl.com.configuration.properties.StorageProperties;
import pl.com.repository.FileRepository;
import pl.com.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository,
                           StorageProperties storageProperties) {

        this.fileRepository = fileRepository;

        this.rootLocation = Paths.get(storageProperties.getRoot());

        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File saveFile(MultipartFile file, String fileName) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            //todo add user
            File newFile = File.builder()
                    .pathToFile(rootLocation.resolve(filename).toString())
                    .fileName(fileName)
                    .createDate(LocalDateTime.now())
                    .modificationDate(LocalDateTime.now())
                    .build();

            Files.copy(file.getInputStream(), rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            return fileRepository.save(newFile);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    public List<File> findAllFiles() { return fileRepository.findAll(); }

    public void deleteFile(String pathToFile) {
        fileRepository.delete(pathToFile);
        try {
            Files.delete(Paths.get(pathToFile));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + pathToFile);
        }
    }

    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }
}
