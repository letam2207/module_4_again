package com.example.bai_mau_2_tb.repository;

import com.example.bai_mau_2_tb.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType,Integer> {
}
