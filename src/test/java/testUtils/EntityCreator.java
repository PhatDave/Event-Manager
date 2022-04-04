package testUtils;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.Team;
import com.hackathlon.hackathlon.entity.user.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityCreator {
    public static EventRequestDto createEvent() {
        EventRequestDto.EventRequestDtoBuilder eventBuilder = EventRequestDto.builder()
                .name("Tetris")
                .confirmationNotAfter(new Date())
                .registrationsNotAfter(new Date())
                .registrationsNotBefore(new Date())
                .maxParticipants(10)
                .weeks(1)
                .teams((List) new ArrayList<Team>());
        return eventBuilder.build();
    }

    public static RegistrationRequestDto createRegistration() {
        ExperienceRequestDto experienceRequestDto = ExperienceRequestDto.builder()
                .repositoryUrl("https://github.com/PhatDave")
                .skills((List) new ArrayList<Skill>())
                .summary("I am a developer of tetris")
                .years(1)
                .build();

        NameRequestDto nameRequestDto = NameRequestDto.builder()
                .first("Phat")
                .last("Dave")
                .build();

        EducationRequestDto educationRequestDto = EducationRequestDto.builder()
                .faculty("Faculty of Tetris")
                .year(1)
                .build();

        PersonalRequestDto personalRequestDto = PersonalRequestDto.builder()
                .name(nameRequestDto)
                .education(educationRequestDto)
                .email("kosmodiskclassic@hotmail.com")
                .phone("0987654321")
                .build();


        RegistrationRequestDto registrationRequestDto = RegistrationRequestDto.builder()
                .experience(experienceRequestDto)
                .personal(personalRequestDto)
                .preferredOS("Windows")
                .motivation("I am a developer of tetris")
                .build();

        return registrationRequestDto;
    }

    private static Object getIfExists(Object obj, Object defaultValue) {
        return obj == null ? defaultValue : obj;
    }
}
