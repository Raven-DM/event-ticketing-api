package com.example.event_ticketing_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_ticketing_api.dto.EventRequest;
import com.example.event_ticketing_api.model.Event;
import com.example.event_ticketing_api.model.Ticket;
import com.example.event_ticketing_api.service.EventService;
import com.example.event_ticketing_api.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final TicketService ticketService;

    public EventController(EventService eventService, TicketService ticketService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid EventRequest request) {
        return new ResponseEntity<>(eventService.createEvent(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findTicketsByEventId(id));
    }
}
