package com.example.event_ticketing_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nomor Tiket tidak boleh kosong")
    private String buyerName;
    

    @NotNull(message = "Event ID tidak boleh kosong")
    private Long eventId;

}