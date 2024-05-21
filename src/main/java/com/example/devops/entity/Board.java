package com.example.devops.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "BOARDS")
@EqualsAndHashCode // equals, hashcode override 해오기
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEXT")
    private String text;
}
