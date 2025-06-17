package com.example.event_ticketing_api.controller;

import com.example.event_ticketing_api.dto.TicketRequest;
import com.example.event_ticketing_api.model.Ticket;
import com.example.event_ticketing_api.service.TicketService;
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

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTicket() throws Exception {
        TicketRequest request = new TicketRequest();
        request.setBuyerName("Martin");
        request.setEventId(5L);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setBuyerName("Martin");
        ticket.setEventId(5L);

        when(ticketService.createTicket(any(TicketRequest.class))).thenReturn(ticket);

        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyerName").value("Martin"))
                .andExpect(jsonPath("$.eventId").value(5));
    }
}
