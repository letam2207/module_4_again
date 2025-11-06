package com.example.quan_ly_cau_thu_jpa.service;

import com.example.quan_ly_cau_thu_jpa.entity.Player;
import com.example.quan_ly_cau_thu_jpa.repository.IPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService{
    private final IPlayerRepository iPlayerRepository;

    public PlayerService(IPlayerRepository iPlayerRepository) {
        this.iPlayerRepository = iPlayerRepository;
    }

    @Override
    public List<Player> findAll() {
        return iPlayerRepository.findAll();
    }

    @Override
    public Player findById(int id) {
        return iPlayerRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        iPlayerRepository.deleteById(id);
    }

    @Override
    public boolean add(Player player) {
        return iPlayerRepository.add(player);
    }
}
