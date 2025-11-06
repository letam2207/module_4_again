package com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.dto.PlayerDto;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Team;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.CloudinaryService;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.IPlayerService;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.ITeamService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final IPlayerService iPlayerService;
    private final ITeamService iTeamService;
    private final CloudinaryService cloudinaryService;

    public PlayerController(IPlayerService iPlayerService,
                            ITeamService iTeamService,
                            CloudinaryService cloudinaryService) {
        this.iPlayerService = iPlayerService;
        this.iTeamService = iTeamService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String listPlayer(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "searchName", defaultValue = "") String searchName,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            ModelMap model) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("name").ascending().and(Sort.by("dob").descending()));

        Page<Player> playerPage;
        if (startDate != null && endDate != null) {
            playerPage = iPlayerService.findAllByNameContainingIgnoreCaseAndDobBetween(searchName, startDate, endDate, pageable);
        } else {
            playerPage = iPlayerService.findAllByNameContaining(searchName, pageable);
        }

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("searchName", searchName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("size", size);

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
        iPlayerService.delete(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá cầu thủ thành công!");
        return "redirect:/players";
    }

    @GetMapping("/save")
    public String showFormAdd(Model model) {
        model.addAttribute("playerDto", new PlayerDto());
        model.addAttribute("teams", iTeamService.findAll());
        return "player/save";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("playerDto") PlayerDto playerDto,
                       BindingResult bindingResult,
                       @RequestParam("avatarFile") MultipartFile avatarFile,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", iTeamService.findAll());
            return "player/save";
        }

        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);

        if (!avatarFile.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(avatarFile);
                player.setAvatar(imageUrl);
            } catch (IOException e) {
                model.addAttribute("teams", iTeamService.findAll());
                model.addAttribute("uploadError", "Lỗi khi tải ảnh lên Cloudinary!");
                return "player/save";
            }
        }

        Team team = iTeamService.findById(playerDto.getTeamId());
        player.setTeam(team);

        iPlayerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm mới cầu thủ thành công!");
        return "redirect:/players";
    }
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Player player = iPlayerService.findById(id);
        if (player == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy cầu thủ!");
            return "redirect:/players";
        }

        PlayerDto playerDto = new PlayerDto();
        BeanUtils.copyProperties(player, playerDto);
        playerDto.setTeamId(player.getTeam().getId());

        model.addAttribute("playerDto", playerDto);
        model.addAttribute("teams", iTeamService.findAll());
        return "player/edit";
    }

    @PostMapping("/update")
    public String updatePlayer(@Valid @ModelAttribute("playerDto") PlayerDto playerDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", iTeamService.findAll());
            return "player/edit";
        }

        Player existingPlayer = iPlayerService.findById(playerDto.getId());
        if (existingPlayer == null) {
            redirectAttributes.addFlashAttribute("mess", "Không tìm thấy cầu thủ để cập nhật!");
            return "redirect:/players";
        }


        BeanUtils.copyProperties(playerDto, existingPlayer, "id", "avatar", "team");


        if (avatarFile != null && !avatarFile.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(avatarFile);
                existingPlayer.setAvatar(imageUrl);
            } catch (IOException e) {
                model.addAttribute("teams", iTeamService.findAll());
                model.addAttribute("uploadError", "Lỗi khi tải ảnh lên Cloudinary!");
                return "player/edit";
            }
        }

        Team team = iTeamService.findById(playerDto.getTeamId());
        existingPlayer.setTeam(team);

        iPlayerService.save(existingPlayer);
        redirectAttributes.addFlashAttribute("mess", "Cập nhật cầu thủ thành công!");
        return "redirect:/players";
    }

}
