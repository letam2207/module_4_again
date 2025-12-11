package com.furama.furamabackend.controller;

import com.furama.furamabackend.model.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FacilityService;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
// QUAN TRỌNG: Cho phép React (cổng 5173 hoặc 3000) gọi sang
@CrossOrigin(origins = "http://localhost:5173")
public class FacilityController {

    @Autowired
    private FacilityService;

    // API lấy danh sách: GET http://localhost:8080/api/facilities
    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacility() {
        List<Facility> facilities = facilityService.findAll();
        if (facilities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    // API thêm mới: POST http://localhost:8080/api/facilities
    @PostMapping
    public ResponseEntity<Facility> saveFacility(@RequestBody Facility facility) {
        return new ResponseEntity<>(facilityService.save(facility), HttpStatus.CREATED);
    }

    // API xóa: DELETE http://localhost:8080/api/facilities/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
