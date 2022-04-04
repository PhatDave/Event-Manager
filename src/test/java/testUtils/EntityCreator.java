package testUtils;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.entity.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityCreator {
    public static EventRequestDto createEvent(String name) {
        EventRequestDto eventRequestDto = new EventRequestDto();
        eventRequestDto.setName((String)getIfExists(name, "Test Event"));
        eventRequestDto.setMaxParticipants(10);
        eventRequestDto.setStartDate(new Date());
        eventRequestDto.setConfirmationNotAfter(new Date());
        eventRequestDto.setRegistrationsNotBefore(new Date());
        eventRequestDto.setWeeks(3);
        eventRequestDto.setTeams((List)new ArrayList<Team>());
        return eventRequestDto;
    }

    private static Object getIfExists(Object obj, Object defaultValue) {
        return obj == null ? defaultValue : obj;
    }
}
