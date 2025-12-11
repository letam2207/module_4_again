package com.example.bai_thi.service;

import com.example.bai_thi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByNameContaining(String name, Pageable pageable);

    void save(Product product);

    Product findById(int id);

    List<Product> findByName(String name);

    void deleteAll(List<Integer> ids);
}
