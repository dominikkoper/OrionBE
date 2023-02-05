package com.dkoper.orion.config;

import com.dkoper.orion.service.ComparisonService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulerConfig {

    private final ComparisonService comparisonService;

    @Async
    @Scheduled(fixedDelay = 60*60*24)
    void cleanUpComparisons(){
        comparisonService.cleanUp();
    }
}
