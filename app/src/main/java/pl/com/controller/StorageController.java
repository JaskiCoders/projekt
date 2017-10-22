package pl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pl.com.model.storage.File;
import pl.com.service.StorageService;

import java.util.List;

@Controller
@RequestMapping("/api/file")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<File>> getFiles() {
        return ResponseEntity.ok().body(storageService.findAllFiles());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<File> postFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        File returnFile = storageService.store(file, fileName);
        return ResponseEntity.ok().body(returnFile);
    }
}
