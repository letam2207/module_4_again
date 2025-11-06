package com.example.ung_dung_quan_ly_cau_thu_spring_boot.service;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.IPlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    private final IPlayerRepository iPlayerRepository;

    public PlayerService(IPlayerRepository iPlayerRepository) {
        this.iPlayerRepository = iPlayerRepository;
    }

    @Override
    public Page<Player> findAllByNameContaining(String searchName, Pageable pageable) {
        return iPlayerRepository.findAllByNameContaining(searchName,pageable);
    }

    @Override
    public List<Player> findAll() {
        return iPlayerRepository.findAll();
    }



    @Override
    public void save(Player player) {
        iPlayerRepository.save(player);
    }

    @Override
    public Player findById(int id) {
        return iPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Player> findAllByNameContainingIgnoreCaseAndDobBetween(String name, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return iPlayerRepository.findAllByNameContainingIgnoreCaseAndDobBetween(name, startDate, endDate, pageable);
    }



    @Override
    public void delete(int id) {
        if (iPlayerRepository.existsById(id)) {
            iPlayerRepository.deleteById(id);
        }
    }
}
