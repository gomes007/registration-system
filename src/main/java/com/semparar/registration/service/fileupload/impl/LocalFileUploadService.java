package com.semparar.registration.service.fileupload.impl;

import com.semparar.registration.service.fileupload.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@Profile("local")
public class LocalFileUploadService implements FileUploadService {
    private static final String UPLOAD_FOLDER = "uploads/%s";

    @Override
    public String upload(final MultipartFile file) {
        try {
            final String fileName = getDestinationFileName() + getScheme(file);
            Path destFile = Paths.get(fileName);
            file.transferTo(destFile);

            return String.format("/%s", fileName);
        } catch (Exception ex) {
            log.error("Erro ao tentar copiar informações de arquivo");
            return null;
        }
    }

    private static String getDestinationFileName() {
        String fileName = "" + System.currentTimeMillis();
        return String.format(UPLOAD_FOLDER, fileName);
    }

    private static String getScheme(MultipartFile file) {
        if (file.getOriginalFilename() == null) return ".txt";

        String[] nameAndScheme = file.getOriginalFilename().split("\\.");
        if (nameAndScheme.length > 1)
            return "." + nameAndScheme[nameAndScheme.length - 1];

        return ".txt";
    }
}
