package com.restfulapi.userrecords.datas.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    private String firstname;
    private String lastname;
    private Gender gender;
=======
  
    private String firstname;
    
    private String lastname;
   
    private Gender gender;
   
>>>>>>> 80eb45dcc86c754cdf73983e07e7f030fecdc1cc
    private LocalDate date_of_birth;
    private LocalDateTime date_created;
    private LocalDateTime date_updated;

}
