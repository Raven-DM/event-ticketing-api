package com.example.event_ticketing_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_ticketing_api.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {}