package com.example.event_ticketing_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_ticketing_api.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(Long eventId);
}
