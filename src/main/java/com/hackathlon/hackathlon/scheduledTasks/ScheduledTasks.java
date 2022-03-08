package com.hackathlon.hackathlon.scheduledTasks;

import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private final EventService eventService;

    @Scheduled(cron = "0 0 12 * * *")
    private void closeEvents() {
        eventService.updateEvents();
    }
}
