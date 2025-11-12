package com.example.ung_dung_quan_ly_cau_thu_spring_boot.controller;

import com.example.ung_dung_quan_ly_cau_thu_spring_boot.dto.PlayerDto;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity.Player;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.exception.PlayerLimitExceededException;
import com.example.ung_dung_quan_ly_cau_thu_spring_boot.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/api/players")
public class RestPlayerController {
    @Autowired
    private IPlayerService iPlayerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAll(){
        List<Player> playerList = iPlayerService.findAll();
        if (playerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id")int id){
        Player player = iPlayerService.findById(id);
        if (player ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PlayerDto playerDto)  {
        Player player = new Player();
        BeanUtils.copyProperties(playerDto,player);
        iPlayerService.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deleteById(@PathVariable("id")int id){
        Player player = iPlayerService.findById(id);
        if (player ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iPlayerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> deleteById(@PathVariable("id")int id,
                                              @RequestBody PlayerDto playerDto) throws PlayerLimitExceededException {
        Player player = iPlayerService.findById(id);
        if (player ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(playerDto,player);
        iPlayerService.save(player);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
