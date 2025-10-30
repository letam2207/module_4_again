package com.example.quan_ly_cau_thu_thymeleaf.repository;

import com.example.quan_ly_cau_thu_thymeleaf.entity.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    boolean add(Player player);
}
