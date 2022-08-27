package com.semparar.registration.service.filedownload;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownloadService {
    Resource download(String fileName) throws IOException;
}
