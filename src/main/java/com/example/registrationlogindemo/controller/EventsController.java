package com.example.registrationlogindemo.controller;
// import java.util.List;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.registrationlogindemo.service.EventsService;
import com.example.registrationlogindemo.dto.UserEventsDto;
import com.example.registrationlogindemo.entity.User;
// import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.security.core.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;

// import jakarta.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

// import org.springframework.ui.Model;

@Controller
@RequestMapping("/events")
public class EventsController {

    private UserService userService;
    private EventsService eventsService;
    
    public EventsController(UserService userService,EventsService eventsService) {
        this.userService = userService;
        this.eventsService = eventsService;
    }

    @GetMapping("/myevents")
    public String listEvents(Model model, Authentication authentication) throws JsonProcessingException {
    String currentUsername = authentication.getName();
    User currentUser = userService.findByEmail(currentUsername);
    List<UserEventsDto> events = eventsService.findAllEventsByUser(currentUser);
    
    // convert the list of events to a JSON string
    ObjectMapper objectMapper = new ObjectMapper();
    String eventsJson = objectMapper.writeValueAsString(events);
    
    // add the events JSON string to the model
    model.addAttribute("eventsJson", eventsJson);
    
    return "calendar";
    }

    @GetMapping("/activities")
    public String getActivities(Model model, Authentication authentication) throws JsonProcessingException {
    String currentUsername = authentication.getName();
    User currentUser = userService.findByEmail(currentUsername);
    List<UserEventsDto> events = eventsService.findAllEventsByUser(currentUser);
    
    // // convert the list of events to a JSON string
    // ObjectMapper objectMapper = new ObjectMapper();
    // String eventsJson = objectMapper.writeValueAsString(events);
    
    // add the events JSON string to the model
    model.addAttribute("eventsJson", events);
    
    return "activities";
    }
    
    @GetMapping("/compte")
    public String getvideos(Model model, Authentication authentication) throws JsonProcessingException {
    String currentUsername = authentication.getName();
    User currentUser = userService.findByEmail(currentUsername);
    List<UserEventsDto> events = eventsService.findAllEventsByUser(currentUser);
    
    // // convert the list of events to a JSON string
    // ObjectMapper objectMapper = new ObjectMapper();
    // String eventsJson = objectMapper.writeValueAsString(events);
    
    // add the events JSON string to the model
    model.addAttribute("eventsJson", events);
    
    return "videos";
    }

    // @PostMapping("/update/event")
    // public String registration(@Valid @ModelAttribute("events") UserEventsDto eventsDto,
    //                            BindingResult result,
    //                            Model model){
    //     UserEvents existing = eventsService.findByid(eventsDto.getId());
    //     if (existing != null) {
    //         result.rejectValue("email", null, "There is already an account registered with that email");
    //     }
    //     if (result.hasErrors()) {
    //         model.addAttribute("user", user);
    //         return "register";
    //     }
    //     userService.saveUser(user);
        
    //     return "videos";
    // }

}
