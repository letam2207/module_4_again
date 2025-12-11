package com.furama.furamabackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Đánh dấu đây là bảng trong DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id;

    private String name;
    private Double area;
    private Double cost;
    private Integer maxPeople;
    private String rentType;
    private String serviceType; // Villa, House, Room

    private String standard;
    private String otherConvenience;
    private Double poolArea;
    private Integer floors;
    private String freeService;

    @Column(columnDefinition = "TEXT") // Cho phép lưu chuỗi dài (link ảnh)
    private String image;
}
