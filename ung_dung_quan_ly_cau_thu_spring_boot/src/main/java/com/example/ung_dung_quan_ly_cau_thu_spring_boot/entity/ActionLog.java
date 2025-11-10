package com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer playerId;
    private String playerName;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime actionTime;
    private String note;
}
