package com.example.registrationlogindemo.repository;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.entity.UserEvents;
import java.util.List;
// import com.example.registrationlogindemo.entity.User;


public interface UserEventsRepository extends JpaRepository<UserEvents,Long>{
    
    List<UserEvents> findByUser(User user);

    List<UserEvents> findAllEventsByUser(User user);

    UserEvents findById(long id);
}
