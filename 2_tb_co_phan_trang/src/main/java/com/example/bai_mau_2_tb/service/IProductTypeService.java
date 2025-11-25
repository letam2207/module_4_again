package com.example.bai_mau_2_tb.service;


import com.example.bai_mau_2_tb.entity.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAll();
    ProductType findById(Integer id);

}
