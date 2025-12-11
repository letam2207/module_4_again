package com.example.bai_thi.service;


import com.example.bai_thi.entity.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAll();
    ProductType findById(Integer id);

}
