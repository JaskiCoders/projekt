package pl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.com.model.storage.File;
import pl.com.service.FileService;

import java.util.List;

@Controller
@RequestMapping("/api/file")
public class StorageController {

    private final FileService fileService;

    @Autowired
    public StorageController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<File>> getFiles() {
        return ResponseEntity.ok().body(fileService.findAllFiles());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<File> postFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        File returnFile = fileService.saveFile(file, fileName);
        return ResponseEntity.ok().body(returnFile);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity<String> removeFile(@RequestParam("pathToFile") String pathToFile) {
        fileService.deleteFile(pathToFile);
        return ResponseEntity.ok().body("File deleted!");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pathToFile}")
    @ResponseBody
    public ResponseEntity<File> getFile(@PathVariable String pathToFile) {
        File file = fileService.load(pathToFile);
        return ResponseEntity.ok().body(file);
    }
}
