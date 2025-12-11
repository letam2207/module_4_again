package com.furama.furamabackend.repository;


import com.furama.furamabackend.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    // JpaRepository đã có sẵn hàm findAll(), save(), deleteById()...
    // Không cần viết thêm gì cả
}