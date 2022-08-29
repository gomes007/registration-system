package com.semparar.registration.service.filedownload.impl;

import com.semparar.registration.service.filedownload.FileDownloadService;
import com.semparar.registration.service.fileupload.FileUploadService;
import com.semparar.registration.service.fileupload.impl.LocalFileUploadService;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.semparar.registration.utils.Constants.UPLOAD_FOLDER;
import static org.junit.jupiter.api.Assertions.*;

class LocalFileDownloadServiceTest {
    private FileUploadService uploadService = new LocalFileUploadService();
    private FileDownloadService downloadService = new LocalFileDownloadService();

    @Test
    public void testDownloadFile() throws IOException {
        // given:
        String fileName = "test.jpg";
        String content = "test";

        MultipartFile file = new MockMultipartFile(
                fileName,
                fileName,
                "image/jpg",
                content.getBytes(StandardCharsets.UTF_8)
        );

        // when:
        String uploadedFileName = this.uploadService.upload(file);
        File savedFile = this.downloadService.download(uploadedFileName).getFile();

        // then:
        String savedContent = new BufferedReader(new FileReader(savedFile)).lines().collect(Collectors.joining());
        assertEquals(content, savedContent);
    }
}