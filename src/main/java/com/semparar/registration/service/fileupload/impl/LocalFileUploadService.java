package com.semparar.registration.service.fileupload.impl;

import com.semparar.registration.service.fileupload.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.semparar.registration.utils.Constants.UPLOAD_FOLDER;
import static java.lang.String.format;

@Slf4j
@Service
@Profile("local")
public class LocalFileUploadService implements FileUploadService {

    @Override
    public String upload(final MultipartFile file) {
        try {
            final String fileName = format("%s%s", System.currentTimeMillis(), getScheme(file));
            final String filePath = format(UPLOAD_FOLDER, fileName);

            Path destFile = Paths.get(filePath);
            file.transferTo(destFile);

            return fileName;
        } catch (Exception ex) {
            log.error("Erro ao tentar copiar informações de arquivo");
            return null;
        }
    }

    private static String getScheme(MultipartFile file) {
        if (file.getOriginalFilename() == null) return ".txt";

        String[] nameAndScheme = file.getOriginalFilename().split("\\.");
        if (nameAndScheme.length > 1)
            return "." + nameAndScheme[nameAndScheme.length - 1];

        return ".txt";
    }
}
