package com.example.ung_dung_quan_ly_cau_thu_spring_boot.service;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Team;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.IPlayerRepository;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.ITeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService{
    private final ITeamRepository iTeamRepository;

    public TeamService(ITeamRepository iTeamRepository) {
        this.iTeamRepository = iTeamRepository;
    }


    @Override
    public List<Team> findAll() {
        return iTeamRepository.findAll();
    }

    @Override
    public Team findById(int id) {
        return iTeamRepository.findById(id).orElse(null);
    }
}
