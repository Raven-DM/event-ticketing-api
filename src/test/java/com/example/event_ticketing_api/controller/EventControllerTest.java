package com.example.event_ticketing_api.controller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.event_ticketing_api.dto.EventRequest;
import com.example.event_ticketing_api.model.Event;
import com.example.event_ticketing_api.service.EventService;

public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        EventRequest request = new EventRequest();
        request.setName("Tech Conference");
        request.setLocation("Jakarta");
        request.setDate(LocalDate.now().plusDays(2));

        Event mockEvent = new Event();
        mockEvent.setId(1L);
        mockEvent.setName(request.getName());
        mockEvent.setLocation(request.getLocation());
        mockEvent.setDate(request.getDate());

        when(eventService.createEvent(request)).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.createEvent(request);
        Event result = response.getBody();

        // Ensure result is not null before accessing its properties
        org.junit.jupiter.api.Assertions.assertNotNull(result);

        assertEquals(mockEvent.getId(), result.getId());
        assertEquals(mockEvent.getName(), result.getName());
        assertEquals(mockEvent.getLocation(), result.getLocation());
        assertEquals(mockEvent.getDate(), result.getDate());
    }
}
