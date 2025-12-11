package service;

import com.furama.furamabackend.model.Facility;


import java.util.List;

public interface FacilityService {
    List<Facility> findAll();
    Facility save(Facility facility);
    void delete(Long id);
    Facility findById(Long id);
}