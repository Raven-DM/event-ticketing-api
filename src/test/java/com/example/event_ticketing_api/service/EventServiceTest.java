package com.example.event_ticketing_api.service;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.event_ticketing_api.dto.EventRequest;
import com.example.event_ticketing_api.model.Event;
import com.example.event_ticketing_api.repository.EventRepository;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent() {
        EventRequest request = new EventRequest();
        request.setName("Test Event");
        request.setLocation("Jakarta");
        request.setDate(LocalDate.now().plusDays(1));

        Event expected = new Event();
        expected.setId(1L);
        expected.setName(request.getName());
        expected.setLocation(request.getLocation());
        expected.setDate(request.getDate());

        when(eventRepository.save(any(Event.class))).thenReturn(expected);

        Event result = eventService.createEvent(request);

        assertEquals("Test Event", result.getName());
        assertEquals("Jakarta", result.getLocation());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
