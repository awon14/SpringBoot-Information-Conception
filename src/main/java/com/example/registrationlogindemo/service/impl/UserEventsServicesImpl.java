package com.example.registrationlogindemo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.dto.UserEventsDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.entity.UserEvents;
// import com.example.registrationlogindemo.entity.User;
// import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.repository.UserEventsRepository;
// import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.EventsService;
import com.example.registrationlogindemo.service.UserService;

@Service
public class UserEventsServicesImpl implements EventsService {
    private UserEventsRepository eventsRepository;
    private UserService userService;

    public UserEventsServicesImpl(UserEventsRepository eventsRepository,UserService userService) {
        this.eventsRepository = eventsRepository;
        this.userService = userService;
        
    }
    @Override
    public Boolean saveEvent(UserEventsDto eventDto) {
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(eventDto.getStart(), DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime endDateTime = LocalDateTime.parse(eventDto.getEnd(), DateTimeFormatter.ISO_DATE_TIME);
            User user = userService.findById(eventDto.getUser());
            UserEvents event = new UserEvents();
            event.setId(eventDto.getId());
            event.setStartDateTime(startDateTime);
            event.setEndDateTime(endDateTime);
            event.setTitle(eventDto.getTitle());
            event.setColor(eventDto.getColor());
            event.setUser(user);
            eventsRepository.save(event);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
        
    public Boolean updateEvent(UserEventsDto eventDto) {
        try {
            
            
            UserEvents event = findById(eventDto.getId());
            
            event.setSecondsWatched(eventDto.getSecondsWatched());
            event.setEventStartDateTime(LocalDateTime.now());
            
            eventsRepository.save(event);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
       

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        
       
        
    }
    
    
    @Override
    public List<UserEventsDto> findAllEvents() {
        // TODO Auto-generated method stub
        List<UserEvents> events = eventsRepository.findAll();
        return events.stream().map((event) -> convertEntityToDto(event))
                .collect(Collectors.toList());
    }

    public List<UserEventsDto> findAllEventsByUser(User user){
        List<UserEvents> events = eventsRepository.findByUser(user);
        return events.stream().map((event) -> convertEntityToDto(event))
                .collect(Collectors.toList());
    }

    private UserEventsDto convertEntityToDto(UserEvents event) {
        UserEventsDto userDto = new UserEventsDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterThree = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateTimeString = event.getStartDateTime().format(formatter);
        String endDateTimeString = event.getEndDateTime().format(formatter);
        String dateWatched = (event.getEventStartDateTime() == null) ? "-" :event.getEventStartDateTime().format(formatterThree);
        String eventStartDateTime = (event.getEventStartDateTime() == null) ? "-" :event.getEventStartDateTime().format(formatter);
        String eventEndDateTime = (event.getEventEndDateTime() == null) ? "-" :event.getEventEndDateTime().format(formatter);
        String EventTimeWatched = (event.getEventStartDateTime() == null) ? "00:00" :event.getEventStartDateTime().format(formatterTwo);
        String EventFinishedTimeWatched = (event.getEventEndDateTime() == null) ? "00.00" :event.getEventEndDateTime().format(formatterTwo);
        Float seconds = (event.getSecondsWatched() == null) ? 0 : event.getSecondsWatched();
        userDto.setColor(event.getColor());
        userDto.setTitle(event.getTitle());
        userDto.setId(event.getId());
        userDto.setStart(startDateTimeString);
        userDto.setEnd(endDateTimeString);
        userDto.setEventStartDateTime(eventStartDateTime);
        userDto.setEventEndDateTime(eventEndDateTime);
        userDto.setEventTimeWatched(EventTimeWatched);
        userDto.setEventFinishedTimeWatched(EventFinishedTimeWatched);
        userDto.setSecondsWatched(seconds);
        userDto.setDateWatched(dateWatched);
        userDto.setUser(event.getUser().getId());
        userDto.setId(event.getId());
        return userDto;
    }

    @Override
    public UserEvents findById(Long id) {       
        Optional<UserEvents> userEventsOptional = eventsRepository.findById(id);
        if (userEventsOptional.isPresent()) {
            return userEventsOptional.get();
        } else {
            // handle the case where the UserEvents object with the given id is not found
            return null;
        }
    }
}
