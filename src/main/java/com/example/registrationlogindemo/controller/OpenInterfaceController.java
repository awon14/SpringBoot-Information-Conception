package com.example.registrationlogindemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.registrationlogindemo.dto.UserEventsDto;
import com.example.registrationlogindemo.service.EventsService;
// import com.example.registrationlogindemo.service.UserService;

@RestController
@RequestMapping(path="/api/v1")
public class OpenInterfaceController {

    
    private EventsService eventsService;
    //private UserService userService;
    public OpenInterfaceController(EventsService eventsService) {
        this.eventsService = eventsService;
       // this.userService = userService;
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
}
