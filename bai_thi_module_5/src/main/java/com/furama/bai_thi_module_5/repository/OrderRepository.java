package com.furama.bai_thi_module_5.repository;

import com.furama.bai_thi_module_5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Có thể viết thêm hàm tìm kiếm nếu muốn chuyển logic tìm kiếm về backend
    // List<Order> findByCodeContaining(String code);
}
