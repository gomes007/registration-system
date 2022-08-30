package com.semparar.registration.jobs;

import com.semparar.registration.service.filecleaner.FileCleanerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class IdleFileCleanerJob implements InitializingBean {
    @Autowired
    private FileCleanerService cleanerService;

    @Override
    @Scheduled(cron = "${file-cleaner-job-schedule}")
    public void afterPropertiesSet() throws Exception {
        log.info("Limpando arquivos sem referencia");
        this.cleanerService.clean();
    }
}
