package com.example.bai_thi.repository;

import com.example.bai_thi.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType,Integer> {
}
