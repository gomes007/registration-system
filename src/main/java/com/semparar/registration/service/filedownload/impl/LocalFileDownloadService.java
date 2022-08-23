package com.semparar.registration.service.filedownload.impl;

import com.semparar.registration.service.filedownload.FileDownloadService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import static com.semparar.registration.utils.Constants.UPLOAD_FOLDER;
import static java.lang.String.format;

@Service
@Profile("local")
public class LocalFileDownloadService implements FileDownloadService {
    @Override
    public Resource download(final String fileName) throws IOException {
        String path = format(UPLOAD_FOLDER, fileName);
        URI uri = Paths.get(path).toUri();
        return new UrlResource(uri);
    }
}
