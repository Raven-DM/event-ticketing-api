package com.example.event_ticketing_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.event_ticketing_api.dto.TicketRequest;
import com.example.event_ticketing_api.model.Ticket;
import com.example.event_ticketing_api.repository.TicketRepository;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTicket() {
        TicketRequest request = new TicketRequest();
        request.setBuyerName("Ovind");
        request.setEventId(2L);

        Ticket savedTicket = new Ticket();
        savedTicket.setId(1L);
        savedTicket.setBuyerName("Ovind");
        savedTicket.setEventId(2L);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        Ticket result = ticketService.createTicket(request);

        assertEquals("Ovind", result.getBuyerName());
        assertEquals(2L, result.getEventId());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }
}
