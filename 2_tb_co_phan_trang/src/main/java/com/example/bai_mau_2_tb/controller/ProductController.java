package com.example.bai_mau_2_tb.controller;

import com.example.bai_mau_2_tb.dto.ProductDto;
import com.example.bai_mau_2_tb.entity.Product;
import com.example.bai_mau_2_tb.entity.ProductType;
import com.example.bai_mau_2_tb.service.IProductService;
import com.example.bai_mau_2_tb.service.IProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;
    private final IProductTypeService productTypeService;

    public ProductController(IProductService productService, IProductTypeService productTypeService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    // ==================== 1️⃣ HIỂN THỊ DANH SÁCH + PHÂN TRANG + TÌM KIẾM ====================
    @GetMapping
    public String getAllProducts(
            @RequestParam(defaultValue = "0") int page,       // Trang hiện tại (mặc định là 0)
            @RequestParam(defaultValue = "") String name,     // Từ khóa tìm kiếm (mặc định rỗng)
            Model model) {

        int pageSize = 5; // Số lượng sản phẩm trên 1 trang

        // Tạo đối tượng Pageable (có thể thêm Sort.by("id").descending() nếu muốn sắp xếp)
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        Page<Product> productPage;

        // Nếu có từ khóa tìm kiếm -> Gọi hàm tìm kiếm có phân trang
        // Nếu không -> Gọi hàm lấy tất cả có phân trang
        if (name != null && !name.isEmpty()) {
            productPage = productService.findByNameContaining(name, pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("productPage", productPage);
        model.addAttribute("search", name); // Gửi lại từ khóa để giữ ở ô input
        return "product/list";
    }

    // ==================== 2️⃣ HIỂN THỊ FORM THÊM MỚI ====================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/create";
    }

    // ==================== 3️⃣ XỬ LÝ THÊM MỚI ====================
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("productDto") ProductDto productDto,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "product/create";
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product, "id", "productType");

        // Gán ProductType theo ID
        ProductType productType = productTypeService.findById(productDto.getProductTypeId());
        product.setProductType(productType);

        productService.save(product);
        redirect.addFlashAttribute("message", " Thêm mới sản phẩm thành công!");
        return "redirect:/product";
    }

    // ==================== 4️⃣ HIỂN THỊ FORM CẬP NHẬT ====================
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            model.addAttribute("message", "Không tìm thấy sản phẩm có ID: " + id);
            return "product/error";
        }

        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        if (product.getProductType() != null) {
            dto.setProductTypeId(product.getProductType().getId());
        }

        model.addAttribute("productDto", dto);
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/update";
    }

    // ==================== 5️⃣ XỬ LÝ CẬP NHẬT ====================
    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("productDto") ProductDto productDto,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "product/update";
        }

        Product existingProduct = productService.findById(productDto.getId());
        if (existingProduct == null) {
            model.addAttribute("message", "Không tìm thấy sản phẩm có ID: " + productDto.getId());
            return "product/error";
        }

        BeanUtils.copyProperties(productDto, existingProduct, "id", "productType");
        ProductType productType = productTypeService.findById(productDto.getProductTypeId());
        existingProduct.setProductType(productType);

        productService.update(existingProduct);
        redirect.addFlashAttribute("message", " Cập nhật sản phẩm thành công!");
        return "redirect:/product";
    }

    // ==================== 6️⃣ XÓA SẢN PHẨM ====================
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("message", " Không tìm thấy sản phẩm để xóa!");
            return "redirect:/product";
        }
        productService.delete(id);
        redirect.addFlashAttribute("message", " Xóa sản phẩm thành công!");
        return "redirect:/product";
    }


    // ==================== XEM CHI TIẾT SẢN PHẨM ====================
    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        var product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("message", " Không tìm thấy sản phẩm có ID: " + id);
            return "redirect:/product";
        }
        model.addAttribute("product", product); // dùng entity để hiển thị
        return "product/detail";
    }

}
