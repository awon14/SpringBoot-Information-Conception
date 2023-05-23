package com.example.registrationlogindemo.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.dto.SubEventDto;
import com.example.registrationlogindemo.entity.SubEvents;
import com.example.registrationlogindemo.entity.UserEvents;
import com.example.registrationlogindemo.repository.SubEventRepository;
// import com.example.registrationlogindemo.repository.UserEventsRepository;
import com.example.registrationlogindemo.service.EventsService;
import com.example.registrationlogindemo.service.SubEventService;


@Service
public class SubEventServicesImpl implements SubEventService{

    private EventsService eventsService;
    private SubEventRepository subEventRepository;

    public SubEventServicesImpl(EventsService eventsService,SubEventRepository subEventRepository) {
        this.eventsService = eventsService;
        this.subEventRepository = subEventRepository;
        
    }

    @Override
    public Boolean saveSubEvent(SubEventDto subEventDto) {
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(subEventDto.getStart(), DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime endDateTime = LocalDateTime.parse(subEventDto.getEnd(), DateTimeFormatter.ISO_DATE_TIME);
            UserEvents event = eventsService.findById(subEventDto.getEvent());
            SubEvents subEvent = new SubEvents();
            subEvent.setId(subEventDto.getId());
            subEvent.setStartDateTime(startDateTime);
            subEvent.setEndDateTime(endDateTime);
            subEvent.setTitle(subEventDto.getTitle());
            subEvent.setColor(subEventDto.getColor());
            subEvent.setEvent(event);
            subEvent.setUrl(subEventDto.getUrl());
            subEventRepository.save(subEvent);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<SubEventDto> findAllEventsByEventId(UserEvents event) {
        List<SubEvents> subEvents = subEventRepository.findAllByEvent(event);
        return subEvents.stream().map((subEvent) -> convertEntityToDto(subEvent))
                .collect(Collectors.toList());
    }

    private SubEventDto convertEntityToDto(SubEvents subEvent) {
        SubEventDto subEventDto = new SubEventDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterThree = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateTimeString = subEvent.getStartDateTime().format(formatter);
        String endDateTimeString = subEvent.getEndDateTime().format(formatter);
        String dateWatched = (subEvent.getEventStartDateTime() == null) ? "-" :subEvent.getEventStartDateTime().format(formatterThree);
        String eventStartDateTime = (subEvent.getEventStartDateTime() == null) ? "-" :subEvent.getEventStartDateTime().format(formatter);
        String eventEndDateTime = (subEvent.getEventEndDateTime() == null) ? "-" :subEvent.getEventEndDateTime().format(formatter);
        String EventTimeWatched = (subEvent.getEventStartDateTime() == null) ? "00:00" :subEvent.getEventStartDateTime().format(formatterTwo);
        String EventFinishedTimeWatched = (subEvent.getEventEndDateTime() == null) ? "00.00" :subEvent.getEventEndDateTime().format(formatterTwo);
        Float seconds = (subEvent.getSecondsWatched() == null) ? 0 : subEvent.getSecondsWatched();
        subEventDto.setColor(subEvent.getColor());
        subEventDto.setTitle(subEvent.getTitle());
        subEventDto.setId(subEvent.getId());
        subEventDto.setStart(startDateTimeString);
        subEventDto.setEnd(endDateTimeString);
        subEventDto.setEventStartDateTime(eventStartDateTime);
        subEventDto.setEventEndDateTime(eventEndDateTime);
        subEventDto.setEventTimeWatched(EventTimeWatched);
        subEventDto.setEventFinishedTimeWatched(EventFinishedTimeWatched);
        subEventDto.setSecondsWatched(seconds);
        subEventDto.setDateWatched(dateWatched);
        subEventDto.setEvent(subEvent.getEvent().getId());
        subEventDto.setUrl(subEvent.getUrl());
        return subEventDto;
    }

}
