package com.example.bai_mau_2_tb.service;

import com.example.bai_mau_2_tb.entity.ProductType;
import com.example.bai_mau_2_tb.repository.IProductRepository;
import com.example.bai_mau_2_tb.repository.IProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductTypeService implements IProductTypeService{

    private final IProductTypeRepository productTypeRepository;

    public ProductTypeService(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }


    @Override
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType findById(Integer id) {
        return productTypeRepository.findById(id).orElse(null);
    }
}
