package com.example.demo.controllers;

import com.example.demo.exception.FileAlreadyExist;
import com.example.demo.repository.FileRepo;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepo fileRepo;

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = fileService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file")
    public ResponseEntity uploadFile(@RequestParam Long id, @RequestParam("file") MultipartFile file) {
        try{
            return ResponseEntity.ok(fileRepo.save(fileService.store(file, id)));
        } catch (FileAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
