package com.example.registrationlogindemo.service;

import java.util.List;


// import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.dto.UserEventsDto;
// import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.entity.User;

public interface EventsService {
    Boolean saveEvent(UserEventsDto eventDto);

    // List<UserEvents> findAllEvents();
    List<UserEventsDto> findAllEvents();
    
    List<UserEventsDto> findAllEventsByUser(User user);
}
