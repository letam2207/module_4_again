package com.furama.bai_thi_module_5.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate importDate;

    private Integer quantity;

    private Long productId;
}