package com.example.ung_dung_quan_ly_cau_thu_spring_boot.aop;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.IPlayerService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class FavoriteAspect {

    private final IPlayerService playerService;
    private final HttpServletRequest request;

    public FavoriteAspect(IPlayerService playerService, HttpServletRequest request) {
        this.playerService = playerService;
        this.request = request;
    }

    @AfterReturning(pointcut = "execution(* com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller.FavoriteController.addFavorite(..))")
    public void afterAddToFavorite(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (args.length > 0 && args[0] instanceof Integer) {
            Integer playerId = (Integer) args[0];
            Player p = playerService.findById(playerId);
            String user = request.getRemoteUser() != null ? request.getRemoteUser() : "anonymous";
            System.out.println(String.format("[AOP-FAVORITE] %s added player(id=%d,name=%s) to favorites at %s",
                    user, playerId, p != null ? p.getName() : "N/A", LocalDateTime.now()));
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller.FavoriteController.removeFavorite(..))")
    public void afterRemoveFromFavorite(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (args.length > 0 && args[0] instanceof Integer) {
            Integer playerId = (Integer) args[0];
            Player p = playerService.findById(playerId);
            String user = request.getRemoteUser() != null ? request.getRemoteUser() : "anonymous";
            System.out.println(String.format("[AOP-FAVORITE] %s removed player(id=%d,name=%s) from favorites at %s",
                    user, playerId, p != null ? p.getName() : "N/A", LocalDateTime.now()));
        }
    }
}
