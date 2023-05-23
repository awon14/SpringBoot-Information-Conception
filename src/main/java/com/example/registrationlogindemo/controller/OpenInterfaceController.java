package com.example.registrationlogindemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.MediaType;

import com.example.registrationlogindemo.dto.SubEventDto;
import com.example.registrationlogindemo.dto.UserEventsDto;
import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.repository.UserEventsRepository;
import com.example.registrationlogindemo.service.EventsService;
// import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.SubEventService;

@RestController
@RequestMapping(path="/api/v1")
public class OpenInterfaceController {

    
    private EventsService eventsService;
    private SubEventService subEventService;
    private UserEventsRepository userEventsRepository;
    //private UserService userService;
    public OpenInterfaceController(EventsService eventsService,SubEventService subEventService,UserEventsRepository userEventsRepository) {
        this.eventsService = eventsService;
        this.subEventService = subEventService;
        this.userEventsRepository = userEventsRepository;
    }
    
    @PostMapping(value ="/addEvent",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addNewEvent(@RequestBody UserEventsDto event){
        
		Boolean is_event_saved = eventsService.saveEvent(event);
        if(is_event_saved){
            return "Event Saved!";
        }else{
            return "No Event Saved!";
        }
	}


    @PostMapping(value ="/updateEvent",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateEvent(@RequestBody UserEventsDto event){
        
		Boolean is_event_saved = eventsService.updateEvent(event);
        if(is_event_saved){
            return "Event Saved!";
        }else{
            return "No Event Saved!";
        }
	}

    @PostMapping(value ="/addSubEvent",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addSubEvent(@RequestBody SubEventDto subEvent){
        
		Boolean is_sub_event_saved = subEventService.saveSubEvent(subEvent);
        if(is_sub_event_saved){
            return "Sub Event Saved!";
        }else{
            return "No Sub Event Saved!";
        }
	}


    @GetMapping(value ="/getSubEvents/{id}")
	public List<SubEventDto> getSubEventsByEventId(@PathVariable Long id){
        UserEvents event = eventsService.findById(id);
		List<SubEventDto> subevents = subEventService.findAllEventsByEventId(event);

        return subevents;
	}

}
