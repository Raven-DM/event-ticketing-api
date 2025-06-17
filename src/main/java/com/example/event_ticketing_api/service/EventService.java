package com.example.event_ticketing_api.service;   

import com.example.event_ticketing_api.dto.EventRequest;
import com.example.event_ticketing_api.model.Event;
import com.example.event_ticketing_api.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());
        return eventRepository.save(event);
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
}