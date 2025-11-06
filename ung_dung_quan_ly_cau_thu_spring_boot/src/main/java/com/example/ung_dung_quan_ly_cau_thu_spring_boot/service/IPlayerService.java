package com.example.ung_dung_quan_ly_cau_thu_spring_boot.service;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Page<Player> findAllByNameContaining(String searchName, Pageable pageable);

    void save (Player player);

    Player findById(int id);

    Page<Player> findAllByNameContainingIgnoreCaseAndDobBetween(String name, LocalDate startDate, LocalDate endDate, Pageable pageable);


    void delete(int id);
}
