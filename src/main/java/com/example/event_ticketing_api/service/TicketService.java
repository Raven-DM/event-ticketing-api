package com.example.event_ticketing_api.service;

import com.example.event_ticketing_api.dto.TicketRequest;
import com.example.event_ticketing_api.model.Ticket;
import com.example.event_ticketing_api.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(TicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setBuyerName(request.getBuyerName());
        ticket.setEventId(request.getEventId());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findTicketsByEventId(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }
}