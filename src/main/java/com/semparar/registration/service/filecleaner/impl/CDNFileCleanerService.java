package com.semparar.registration.service.filecleaner.impl;

import com.semparar.registration.service.EmployeeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CDNFileCleanerService extends FileCleanerBaseService {
    protected CDNFileCleanerService(final EmployeeService service) {
        super(service);
    }

    @Override
    public void clean() {

    }
}
