package com.makhnov.backup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "todo")
@ToString
public class Todo {
    @Id
    @Column(updatable = false)
    private long id;
    private String subject;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "backup_id", insertable = false, updatable = false)
    @JsonIgnore
    private Backup backup;

    @Column(name = "backup_id", updatable = false)
    private long backupId;

    @Column(name = "user_id", updatable = false)
    private long userId;
}
