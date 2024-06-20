package org.javaacademy.youtube.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
}
