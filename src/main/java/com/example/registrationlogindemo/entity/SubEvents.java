package com.example.registrationlogindemo.entity;

import java.time.LocalDateTime;
// import java.util.List;
// import java.util.List;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subevents")
public class SubEvents {
    @Id
    @SequenceGenerator(
        name = "sub_event_sequence",
        sequenceName = "sub_event_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sub_event_sequence"
    )
    private Long id;
    @Column(name = "startDateTime", nullable = false)
    private LocalDateTime startDateTime;
    
    @Column(name = "endDateTime", nullable = true)
    private LocalDateTime endDateTime;


    @Column(name = "eventStartDateTime", nullable = true)
    private LocalDateTime eventStartDateTime;

    @Column(name = "secondsWatched", nullable = true)
    private Float secondsWatched;

    @Column(name = "eventEndDateTime", nullable = true)
    private LocalDateTime eventEndDateTime;

    @Column(name = "note", nullable = true)
    private String note;
    
    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "color", nullable = true)
    private String color;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private UserEvents event;
   

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubEvents getEvents() {
        return this;
    }
}
