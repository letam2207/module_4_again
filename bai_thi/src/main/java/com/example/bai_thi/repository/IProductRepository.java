package com.example.bai_thi.repository;

import com.example.bai_thi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    @Query("select s from products s where s.name like %:name%")
    List<Product> findByName(@Param("name") String name);



    Page<Product> findByNameContaining(String name, Pageable pageable);
}
