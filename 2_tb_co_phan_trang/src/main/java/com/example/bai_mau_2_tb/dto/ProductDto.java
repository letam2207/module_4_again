package com.example.bai_mau_2_tb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được vượt quá 100 ký tự")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @PositiveOrZero(message = "Giá sản phẩm phải lớn hơn hoặc bằng 0")
    private Double price;

    @NotNull(message = "Ngày sản xuất không được để trống")
    @PastOrPresent(message = "Ngày sản xuất không được lớn hơn ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;

    @Size(max = 200, message = "Mô tả không được vượt quá 200 ký tự")
    private String describe;

    @NotBlank(message = "Hãng sản xuất không được để trống")
    private String manufacturer;

    @NotNull(message = "Loại sản phẩm không được để trống")
    private Integer productTypeId;

    private String productTypeName;
}
