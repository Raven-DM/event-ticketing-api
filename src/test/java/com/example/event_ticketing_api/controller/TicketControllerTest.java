package com.example.event_ticketing_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.event_ticketing_api.dto.TicketRequest;
import com.example.event_ticketing_api.model.Ticket;
import com.example.event_ticketing_api.service.TicketService;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        // Arrange
        TicketRequest request = new TicketRequest();
        request.setBuyerName("John Doe");
        request.setEventId(1L);

        Ticket mockTicket = new Ticket();
        mockTicket.setId(1L);
        mockTicket.setBuyerName(request.getBuyerName());
        mockTicket.setEventId(request.getEventId());

        when(ticketService.createTicket(request)).thenReturn(mockTicket);

        // Act
        ResponseEntity<Ticket> response = ticketController.createTicket(request);
        Ticket result = response.getBody();

        // Assert
        org.junit.jupiter.api.Assertions.assertNotNull(result, "Ticket result should not be null");
        if (result != null) {
            assertEquals(mockTicket.getId(), result.getId());
            assertEquals(mockTicket.getBuyerName(), result.getBuyerName());
            assertEquals(mockTicket.getEventId(), result.getEventId());
        }
    }
}
