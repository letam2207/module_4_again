package com.example.ung_dung_quan_ly_cau_thu_spring_boot.service;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Team;

import java.util.List;

public interface ITeamService {
    List<Team> findAll();
    Team findById(int id);
}
