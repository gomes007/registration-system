package com.semparar.registration.controller;

import com.semparar.registration.service.filedownload.FileDownloadService;
import com.semparar.registration.service.fileupload.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileManagementController {
    @Autowired
    private FileUploadService uploadService;

    @Autowired
    private FileDownloadService downloadService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return this.uploadService.upload(file);
    }

    @GetMapping(value = "/{fileName}")
    public Resource serveFile(@PathVariable("fileName") String fileName) throws IOException {
        return this.downloadService.download(fileName);
    }
}
