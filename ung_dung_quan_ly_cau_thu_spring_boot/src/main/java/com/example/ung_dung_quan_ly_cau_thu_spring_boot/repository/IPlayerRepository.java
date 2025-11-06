package com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface IPlayerRepository extends JpaRepository<Player,Integer> {
    Page<Player> findAllByNameContaining(String searchName, Pageable pageable);

    Page<Player> findAllByNameContainingIgnoreCaseAndDobBetween(String name, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
