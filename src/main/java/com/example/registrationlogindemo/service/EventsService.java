package com.example.registrationlogindemo.service;

import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.dto.UserEventsDto;
// import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.entity.UserEvents;

public interface EventsService {
    Boolean saveEvent(UserEventsDto eventDto);

    UserEvents findById(Long id);
    
    List<UserEventsDto> findAllEvents();
    
    List<UserEventsDto> findAllEventsByUser(User user);

    Boolean updateEvent(UserEventsDto event);
}
