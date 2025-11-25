package com.example.bai_mau_2_tb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "production_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;
    @Column(name = "describe_product")
    private String describe;
    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private ProductType productType;
}
