package com.furama.bai_thi_module_5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Lombok tự sinh Getter/Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String type;
}
