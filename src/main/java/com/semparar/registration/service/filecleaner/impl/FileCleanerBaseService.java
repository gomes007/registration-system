package com.semparar.registration.service.filecleaner.impl;

import com.semparar.registration.service.EmployeeService;
import com.semparar.registration.service.filecleaner.FileCleanerService;

import java.util.List;

public abstract class FileCleanerBaseService implements FileCleanerService {
    private static final List<String> INVALID_FILES = List.of(".gitkeep", "default.jpeg");
    private final EmployeeService service;

    protected FileCleanerBaseService(final EmployeeService service) {
        this.service = service;
    }

    private boolean isEmployeeProfilePhoto(final String fileName) {
        var page =  0;
        var size = 10;
        var employeesProfilePhotos = this.service.listEmployeesProfilePhotoReferences(page, size);

        while (!employeesProfilePhotos.isEmpty()) {
            if (employeesProfilePhotos.contains(fileName))
                return true;
            page++;
            employeesProfilePhotos = this.service.listEmployeesProfilePhotoReferences(page, size);
        }

        return false;
    }

    protected boolean isAttachedToSomeContext(final String fileName) {
        return isEmployeeProfilePhoto(fileName);
    }

    protected boolean isValid(final String fileName) {
        return !INVALID_FILES.contains(fileName);
    }
}
