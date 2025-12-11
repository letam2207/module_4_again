package com.furama.bai_thi_module_5.controller;

import com.furama.bai_thi_module_5.entity.Product;
import com.furama.bai_thi_module_5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép React truy cập
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // API tạo mẫu dữ liệu (chạy 1 lần để có data test)
    @PostMapping("/init")
    public String initData() {
        if(productRepository.count() == 0) {
            Product p1 = new Product(); p1.setName("Xiaomi 14"); p1.setPrice(12500000.0); p1.setType("Điện thoại");
            Product p2 = new Product(); p2.setName("Dell XPS"); p2.setPrice(45000000.0); p2.setType("Laptop");
            productRepository.save(p1);
            productRepository.save(p2);
            return "Đã tạo dữ liệu mẫu";
        }
        return "Dữ liệu đã có sẵn";
    }
}
