package com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.IPlayerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/players/favorite")
public class FavoriteController {

    private final IPlayerService playerService;

    public FavoriteController(IPlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("/add/{id}")
    public String addFavorite(@PathVariable("id") Integer id,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Player player = playerService.findById(id);
        if (player == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy cầu thủ!");
            return "redirect:/players";
        }

        List<Player> favorites = (List<Player>) session.getAttribute("favorites");
        if (favorites == null) {
            favorites = new ArrayList<>();
        }

        boolean exists = favorites.stream().anyMatch(p -> p.getId().equals(player.getId()));
        if (!exists) {
            favorites.add(player);
            session.setAttribute("favorites", favorites);
            redirectAttributes.addFlashAttribute("mess", "Đã thêm cầu thủ vào danh sách yêu thích ❤️");
        } else {
            redirectAttributes.addFlashAttribute("mess", "Cầu thủ này đã có trong danh sách yêu thích!");
        }

        return "redirect:/players";
    }

    @PostMapping("/remove/{id}")
    public String removeFavorite(@PathVariable("id") Integer id,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        List<Player> favorites = (List<Player>) session.getAttribute("favorites");
        if (favorites != null) {
            favorites.removeIf(p -> p.getId().equals(id));
            session.setAttribute("favorites", favorites);
            redirectAttributes.addFlashAttribute("mess", "Đã xoá cầu thủ khỏi danh sách yêu thích 💔");
        }
        return "redirect:/players/favorite/list";
    }

    @GetMapping("/list")
    public String viewFavorites(HttpSession session, Model model) {
        List<Player> favorites = (List<Player>) session.getAttribute("favorites");
        model.addAttribute("favorites", favorites);
        return "player/favorite";
    }
}
