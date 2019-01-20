package com.makhnov.backup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Backup {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("backupId")
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private String status;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "backup")
    private List<Todo> todos;
}
