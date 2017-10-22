package pl.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.com.exception.StorageException;
import pl.com.model.File;
import pl.com.configuration.properties.StorageProperties;
import pl.com.repository.FileRepository;
import pl.com.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Component
public class StorageServiceImpl implements StorageService {

    private FileRepository fileRepository;
    private StorageProperties storageProperties;

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(FileRepository fileRepository,
                              StorageProperties storageProperties) {

        this.fileRepository = fileRepository;
        this.storageProperties = storageProperties;

        this.rootLocation = Paths.get(storageProperties.getRoot());
    }

    public void store(MultipartFile file, String fileName) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            //todo add user
            File newFile = File.builder()
                    .fileName(fileName)
                    .createDate(LocalDateTime.now())
                    .modificationDate(LocalDateTime.now())
                    .pathToFile(rootLocation.toString())
                    .build();

            Files.copy(file.getInputStream(), rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            fileRepository.save(newFile);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
}
