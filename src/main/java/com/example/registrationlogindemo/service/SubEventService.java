package com.example.registrationlogindemo.service;
import java.util.List;

import com.example.registrationlogindemo.dto.SubEventDto;
import com.example.registrationlogindemo.entity.UserEvents;

public interface SubEventService {
    Boolean saveSubEvent(SubEventDto subEventDto);

    List<SubEventDto> findAllEventsByEventId(UserEvents event);

}
