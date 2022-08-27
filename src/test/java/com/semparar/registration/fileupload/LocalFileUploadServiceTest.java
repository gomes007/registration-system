package com.semparar.registration.fileupload;

import com.semparar.registration.service.fileupload.FileUploadService;
import com.semparar.registration.service.impl.LocalFileUploadService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.semparar.registration.utils.Constants.UPLOAD_FOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalFileUploadServiceTest {
    private FileUploadService service = new LocalFileUploadService();

    @Test
    public void testUploadFile() throws FileNotFoundException {
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
        String uploadedFileName = this.service.upload(file);
        String location = String.format(UPLOAD_FOLDER, uploadedFileName);

        // then:
        File savedFile = Paths.get(location).toFile();
        String savedContent = new BufferedReader(new FileReader(savedFile)).lines().collect(Collectors.joining());
        assertEquals(content, savedContent);
    }
}
