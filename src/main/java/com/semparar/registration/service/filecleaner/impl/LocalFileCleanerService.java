package com.semparar.registration.service.filecleaner.impl;

import com.semparar.registration.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static com.semparar.registration.utils.Constants.UPLOAD_FOLDER;
import static java.lang.String.format;

@Slf4j
@Service
@Profile("local")
public class LocalFileCleanerService extends FileCleanerBaseService {

    public LocalFileCleanerService(final EmployeeService service) {
        super(service);
    }

    @Override
    public void clean() {
        String path = format(UPLOAD_FOLDER, "");
        File uploadDirectory = Paths.get(path).toFile();
        if (canRead(uploadDirectory)) {
            var files = uploadDirectory.listFiles();
            Optional.ofNullable(files).ifPresent(filesRef -> {
                for (var file : filesRef) {
                    var fileName = file.getName();
                    if (this.isValid(fileName) && !this.isAttachedToSomeContext(fileName)) {
                        var deleted = file.delete();
                        log.info("deleted file {}: {}", fileName, deleted);
                    }
                }
            });
        }
    }

    private static boolean canRead(File directory) {
        return exists(directory) && hasFiles(directory);
    }

    private static boolean exists(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    private static boolean hasFiles(File directory) {
        var files = directory.list();
        return files != null && files.length > 0;
    }
}
