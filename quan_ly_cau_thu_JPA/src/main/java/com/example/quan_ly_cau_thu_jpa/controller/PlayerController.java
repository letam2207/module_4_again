package com.example.quan_ly_cau_thu_jpa.controller;

import com.example.quan_ly_cau_thu_jpa.entity.Player;
import com.example.quan_ly_cau_thu_jpa.service.IPlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final IPlayerService iPlayerService;

    public PlayerController(IPlayerService iPlayerService) {
        this.iPlayerService = iPlayerService;
    }


    @GetMapping
    public String listPlayer(Model model) {
        List<Player> playerList = iPlayerService.findAll();
        model.addAttribute("playerList", playerList);
        return "player/list";
    }

    @GetMapping("/detail/{id}")
    public String detailPlayer(@PathVariable("id") int id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        Player player = iPlayerService.findById(id);

        if (player == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy cầu thủ!");
            return "redirect:/players";
        }

        model.addAttribute("player", player);
        return "player/detail";
    }

    @PostMapping("/delete/{id}")
    public String deletePlayer(@PathVariable("id") int id,
                               RedirectAttributes redirectAttributes) {
        iPlayerService.deleteById(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá cầu thủ thành công!");
        return "redirect:/players";
    }

    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("player", new Player());
        return "player/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Player player,
                       RedirectAttributes redirectAttributes) {
        iPlayerService.add(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới cầu thủ thành công!");
        return "redirect:/players";
    }

}
