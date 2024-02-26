package ru.voronina.gb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "header")
    private String header;

    @Column(nullable = false,  name = "content")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_of_creation")
    private LocalDateTime dateCreate;



}
