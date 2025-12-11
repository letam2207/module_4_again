package com.furama.bai_thi_module_5.controller;

import com.furama.bai_thi_module_5.dto.OrderDto;
import com.furama.bai_thi_module_5.entity.Order;
import com.furama.bai_thi_module_5.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // --- SỬA HÀM TẠO MỚI ---
    @PostMapping
    // Nhận vào OrderDto thay vì Order
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto) {

        // B1: Tạo Entity mới
        Order order = new Order();

        // B2: Chép dữ liệu từ DTO sang Entity (Mapping)
        // Cách này thủ công nhưng dễ hiểu nhất. Sau này bạn có thể dùng BeanUtils.copyProperties()
        order.setCode(orderDto.getCode());
        order.setImportDate(orderDto.getImportDate());
        order.setQuantity(orderDto.getQuantity());
        order.setProductId(orderDto.getProductId());

        // B3: Lưu (Lúc này ID tự sinh, không lo lỗi ID cũ nữa)
        Order newOrder = orderRepository.save(order);

        return ResponseEntity.ok(newOrder);
    }

    // --- SỬA HÀM CẬP NHẬT ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {

        // B1: Tìm xem có trong DB không
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        // B2: Cập nhật dữ liệu mới từ DTO vào Entity cũ
        order.setCode(orderDto.getCode());
        order.setImportDate(orderDto.getImportDate());
        order.setQuantity(orderDto.getQuantity());
        order.setProductId(orderDto.getProductId());

        // B3: Lưu đè
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

