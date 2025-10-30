package com.example.quan_ly_cau_thu_thymeleaf.service;

import com.example.quan_ly_cau_thu_thymeleaf.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    boolean add(Player player);
}
