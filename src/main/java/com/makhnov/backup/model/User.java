package com.makhnov.backup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(updatable = false)
    private long id;
    @JsonProperty("username")
    private String userName;
    private String email;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Todo> todos;
}
