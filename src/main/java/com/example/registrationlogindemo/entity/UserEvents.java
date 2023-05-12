package com.example.registrationlogindemo.entity;

import java.time.LocalDateTime;
// import java.util.List;
// import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="events")
public class UserEvents {
    @Id
    @SequenceGenerator(
        name = "user_event_sequence",
        sequenceName = "user_event_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "user_event_sequence"
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
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "color", nullable = true)
    private String color;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
   

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEvents getEvents() {
        return this;
    }

}
