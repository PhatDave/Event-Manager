package com.hackathlon.hackathlon.scheduledTasks;

import com.hackathlon.hackathlon.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private final EventService eventService;

    @Scheduled(cron = "0 0 12 * * *")
    private void closeEvents() {
        eventService.updateEvents();
    }
}
