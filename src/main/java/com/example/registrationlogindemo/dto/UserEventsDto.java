package com.example.registrationlogindemo.dto;

import lombok.*;

// import java.time.LocalDateTime;

// import com.example.registrationlogindemo.entity.User;

import jakarta.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventsDto {
    private Long id;
    @NotEmpty
    private String start;
    @NotEmpty
    private String end;
    @NotEmpty(message = "Email should not be empty")
    
    @NotEmpty(message = "Password should not be empty")
    private String title;

    private String color;

    private Long user;

    private String eventEndDateTime;

    private Float secondsWatched;
    
    private String eventStartDateTime;

    private String eventTimeWatched;

    private String eventFinishedTimeWatched;

    private String dateWatched;

}
