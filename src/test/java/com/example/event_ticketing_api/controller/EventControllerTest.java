package com.example.event_ticketing_api.controller;

import com.example.event_ticketing_api.dto.EventRequest;
import com.example.event_ticketing_api.model.Event;
import com.example.event_ticketing_api.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateEvent() throws Exception {
        EventRequest request = new EventRequest();
        request.setName("Event X");
        request.setLocation("Bandung");
        request.setDate(LocalDate.now().plusDays(1));

        Event event = new Event();
        event.setId(1L);
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());

        when(eventService.createEvent(any(EventRequest.class))).thenReturn(event);

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Event X"))
                .andExpect(jsonPath("$.location").value("Bandung"));
    }
}
