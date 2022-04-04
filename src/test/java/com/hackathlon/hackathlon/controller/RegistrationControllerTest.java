package com.hackathlon.hackathlon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.Team;
import com.hackathlon.hackathlon.repository.EventRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import testUtils.EntityCreator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private static EventRepository eventRepository;

    @BeforeAll
    static void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -3);

        ArrayList<Event> events = new ArrayList<>();

        Event event1 = new Event();
        event1.setName("Event1");
        event1.setTeams((List) new ArrayList<Team>());
        event1.setRegistrationsNotAfter(new Date());
        events.add(event1);

        Event event2 = new Event();
        event1.setName("Event2");
        event1.setTeams((List) new ArrayList<Team>());
        event1.setRegistrationsNotAfter(calendar.getTime());
        events.add(event2);

        eventRepository.saveAll(events);
    }

    @Test
    public void postRegistration() throws Exception {
        RegistrationRequestDto registrationRequestDto = EntityCreator.createRegistration();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(registrationRequestDto);

        mockMvc.perform(post("/event/1/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/event/123123/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}