package com.example.ung_dung_quan_ly_cau_thu_spring_boot.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerLimitExceededException.class)
    public String handlePlayerLimit(PlayerLimitExceededException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/player-limit";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Có lỗi xảy ra: " + ex.getMessage());
        return "error/general";
    }
}
