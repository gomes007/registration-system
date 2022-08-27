package com.semparar.registration.service.fileupload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileUploadService {
    String upload(MultipartFile file);
}
