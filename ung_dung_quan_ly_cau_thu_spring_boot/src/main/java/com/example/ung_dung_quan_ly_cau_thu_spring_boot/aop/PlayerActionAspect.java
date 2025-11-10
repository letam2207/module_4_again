package com.example.ung_dung_quan_ly_cau_thu_spring_boot.aop;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.repository.IPlayerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PlayerActionAspect {

    private final IPlayerRepository playerRepository;

    public PlayerActionAspect(IPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @AfterReturning(pointcut = "execution(* com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.IPlayerService.togglePlayerStatus(..))", returning = "player")
    public void afterToggle(JoinPoint joinPoint, Object player) {
        if (player instanceof Player) {
            Player p = (Player) player;
            System.out.println("[AOP-LOG] Player id=" + p.getId() + " tên=" + p.getName()
                    + " đã chuyển trạng thái thành: " + p.getStatus());
        } else {
            System.out.println("[AOP-LOG] togglePlayerStatus returned: " + player);
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller.PlayerController.listPlayer(..))")
    public void afterListPlayers(JoinPoint joinPoint) {
        long count = playerRepository.countByStatus("Đăng ký đá");
        System.out.println("[AOP-LOG] Tổng số cầu thủ 'Đăng ký đá' hiện tại = " + count);
    }
}
