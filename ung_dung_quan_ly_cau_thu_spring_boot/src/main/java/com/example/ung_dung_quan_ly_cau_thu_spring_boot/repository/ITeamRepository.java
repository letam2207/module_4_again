package com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team,Integer> {
}
