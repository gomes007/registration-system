package com.semparar.registration.service.fileupload.impl;

import com.semparar.registration.service.fileupload.FileUploadService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Profile("prod")
public class CDNFileUploadService implements FileUploadService {
    @Override
    public String upload(final MultipartFile file) {
        return null;
    }
}
