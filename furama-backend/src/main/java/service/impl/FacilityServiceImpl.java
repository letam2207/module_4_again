package service.impl;

import com.furama.furamabackend.model.Facility;
import com.furama.furamabackend.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FacilityService;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public void delete(Long id) {
        facilityRepository.deleteById(id);
    }

    @Override
    public Facility findById(Long id) {
        return facilityRepository.findById(id).orElse(null);
    }
}