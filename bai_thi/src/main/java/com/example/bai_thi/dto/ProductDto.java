package com.example.bai_thi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên sản phẩm phải là 5 đến 50 ký tự")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 100000, message = "Giá phải > 100.000 VNĐ")
    private Double price;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

    @NotNull(message = "Loại sản phẩm không được để trống")
    private Integer productTypeId;

    private String productTypeName;
}
