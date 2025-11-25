package com.example.bai_mau_2_tb.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("message", " Vui lòng nhập số.");
        return "product/error";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleNotFound(ProductNotFoundException ex, Model model) {
        model.addAttribute("message", " " + ex.getMessage());
        return "product/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("message", " Có lỗi xảy ra: " + ex.getMessage());
        return "product/error";
    }
}
