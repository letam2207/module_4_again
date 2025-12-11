package com.furama.bai_thi_module_5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    // DTO không cần ID (khi tạo mới)

    @NotBlank(message = "Mã đơn hàng không được để trống")
    @Pattern(regexp = "^DH\\d{3}$", message = "Mã đơn hàng phải có dạng DHxxx (VD: DH001)")
    private String code;

    @NotNull(message = "Ngày nhập không được để trống")
    @PastOrPresent(message = "Ngày nhập không được lớn hơn ngày hiện tại")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate importDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Vui lòng chọn sản phẩm")
    private Long productId;
}
