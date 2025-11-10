package com.example.ung_dung_quan_ly_cau_thu_spring_boot.service;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.ActionLog;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.exception.PlayerLimitExceededException;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.ActionLogRepository;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.IPlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    private final IPlayerRepository iPlayerRepository;
    private final ActionLogRepository logRepository;
    private static final String STATUS_REGISTERED = "Đăng ký đá";
    private static final String STATUS_SUBSTITUTE = "Dự bị";
    private static final int MAX_REGISTERED = 11;

    public PlayerService(IPlayerRepository iPlayerRepository,ActionLogRepository logRepository) {
        this.iPlayerRepository = iPlayerRepository;
        this.logRepository = logRepository;
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
    @Override
    public long countByStatus(String status) {
        return iPlayerRepository.countByStatus(status);
    }

    @Override
    @Transactional
    public Player togglePlayerStatus(Integer id) {
        Player player = iPlayerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cầu thủ id=" + id));

        String oldStatus = player.getStatus() == null ? STATUS_SUBSTITUTE : player.getStatus();

        if (STATUS_SUBSTITUTE.equals(oldStatus)) {
            long currentRegistered = iPlayerRepository.countByStatus(STATUS_REGISTERED);
            if (currentRegistered >= MAX_REGISTERED) {
                // Ném custom exception nếu vượt giới hạn
                throw new PlayerLimitExceededException("Không thể đăng ký: đã có " + MAX_REGISTERED + " cầu thủ đăng ký.");
            }
            player.setStatus(STATUS_REGISTERED);
        } else {
            player.setStatus(STATUS_SUBSTITUTE);
        }

        Player saved = iPlayerRepository.save(player);

        ActionLog log = new ActionLog();
        log.setPlayerId(player.getId());
        log.setPlayerName(player.getName());
        log.setOldStatus(oldStatus);
        log.setNewStatus(saved.getStatus());
        log.setActionTime(LocalDateTime.now());
        log.setNote("Toggle trạng thái bởi hệ thống");
        logRepository.save(log);

        return saved;
    }
}
