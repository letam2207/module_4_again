package com.example.ung_dung_quan_ly_cau_thu_spring_boot.exception;

public class PlayerLimitExceededException extends RuntimeException {
    public PlayerLimitExceededException(String message) {
        super(message);
    }
}
