package com.semparar.registration.service.filedownload.impl;

import com.semparar.registration.service.filedownload.FileDownloadService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CDNFileDownloadService implements FileDownloadService {
    @Override
    public Resource download(final String fileName) {
        return null;
    }
}
