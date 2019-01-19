package com.makhnov.backup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "backup")
public class BackUp {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private String status;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL})
    private List<User> users;
}
