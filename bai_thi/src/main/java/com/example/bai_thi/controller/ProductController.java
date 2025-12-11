package com.example.bai_thi.controller;

import com.example.bai_thi.dto.ProductDto;
import com.example.bai_thi.entity.Product;
import com.example.bai_thi.entity.ProductType;
import com.example.bai_thi.service.IProductService;
import com.example.bai_thi.service.IProductTypeService;
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


    @GetMapping
    public String getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String name,
            Model model) {

        int pageSize = 5;

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        Page<Product> productPage;

        if (name != null && !name.isEmpty()) {
            productPage = productService.findByNameContaining(name, pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("productPage", productPage);
        model.addAttribute("search", name);
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/create";
    }

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

        ProductType productType = productTypeService.findById(productDto.getProductTypeId());
        product.setProductType(productType);

        productService.save(product);
        redirect.addFlashAttribute("message", " Thêm mới sản phẩm thành công!");
        return "redirect:/product";
    }


    @PostMapping("/delete-all")
    public String deleteAll(@RequestParam(required = false) List<Integer> ids,
                            RedirectAttributes redirect) {
        if (ids == null || ids.isEmpty()) {
            redirect.addFlashAttribute("message", "Chưa chọn sản phẩm nào để xóa!");
        } else {
            productService.deleteAll(ids);
            redirect.addFlashAttribute("message", "Đã xóa " + ids.size() + " sản phẩm thành công!");
        }
        return "redirect:/product";
    }

}
