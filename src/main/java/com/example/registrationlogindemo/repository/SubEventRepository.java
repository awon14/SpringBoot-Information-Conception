package com.example.registrationlogindemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.registrationlogindemo.dto.SubEventDto;
import com.example.registrationlogindemo.entity.SubEvents;

import com.example.registrationlogindemo.entity.UserEvents;


public interface SubEventRepository extends JpaRepository<SubEvents,Long>{
    List<SubEvents> findAllSubEventsEventsByEventId(UserEvents event);

    List<SubEvents> findAllByEvent(UserEvents event);
}

