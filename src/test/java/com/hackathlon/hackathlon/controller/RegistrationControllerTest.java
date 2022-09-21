package com.hackathlon.hackathlon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.entity.Event;
import com.hackathlon.entity.Registration;
import com.hackathlon.entity.Team;
import com.hackathlon.repository.EventRepository;
import com.hackathlon.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import testUtils.EntityCreator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.add(Calendar.DATE, 3);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.add(Calendar.DATE, -3);

        ArrayList<Event> events = new ArrayList<>();

        Event event1 = new Event();
        event1.setID(1L);
        event1.setName("Event1");
        event1.setTeams((List) new ArrayList<Team>());
        event1.setRegistrations((List) new ArrayList<>());
        event1.setRegistrationsNotAfter(calendar1.getTime());
        events.add(event1);

        Event event2 = new Event();
        event2.setID(2L);
        event2.setName("Event2");
        event2.setTeams((List) new ArrayList<Team>());
        event2.setRegistrations((List) new ArrayList<>());
        event2.setRegistrationsNotAfter(calendar2.getTime());
        events.add(event2);

        Mockito.when(eventRepository.findAll()).thenReturn(events);
        Mockito.when(eventRepository.findById(1L)).thenReturn(java.util.Optional.of(event1));
        Mockito.when(eventRepository.findById(2L)).thenReturn(java.util.Optional.of(event2));

        Mockito.when(eventRepository.save(Mockito.any(Event.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(registrationRepository.save(Mockito.any(Registration.class))).thenAnswer(i -> i.getArguments()[0]);

        Registration registration = new Registration();
        registration.setID(1L);
        registration.setEvent(event1);
        registration.setUUID("uuid");

        Mockito.when(registrationRepository.findByUUID("uuid")).thenReturn(java.util.Optional.of(registration));
    }

    @Test
    public void postRegistration() throws Exception {
        RegistrationRequestDto registrationRequestDto = EntityCreator.createRegistration();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(registrationRequestDto);

        // todo get uuid how?
        mockMvc.perform(post("/event/1/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/event/1/registrations/1"));

        mockMvc.perform(post("/event/2/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isMethodNotAllowed());

        mockMvc.perform(post("/event/123123/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRegistration() throws Exception {
        mockMvc.perform(delete("/event/1/registrations/uuid"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/event/1/registrations/uuid"))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/event/13/registrations/uuid"))
                .andExpect(status().isNotFound());
    }
}
