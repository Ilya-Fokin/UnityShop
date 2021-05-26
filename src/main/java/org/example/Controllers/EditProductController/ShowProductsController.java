package org.example.Controllers.EditProductController;

import org.example.Domains.Product;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.SizeService.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private SizeService sizeService;

    @GetMapping("/get_all_products/{Chapter}/{Category}")
    public List<Product> showMensTShirts(@PathVariable(name = "Chapter") String chapter, @PathVariable(name = "Category") String category) {
        if (chapter.equals("Mens")) {
            if (category.equals("T-shirt")) {
                return productService.getAllByCategoryAndChapter("Футболки", "Мужчины");
            } else
            if (category.equals("Hoody")) {
                return productService.getAllByCategoryAndChapter("Худи", "Мужчины");
            } else
            if (category.equals("Jacket")) {
                return productService.getAllByCategoryAndChapter("Куртки", "Мужчины");
            } else
            if (category.equals("Trousers")) {
                return productService.getAllByCategoryAndChapter("Брюки", "Мужчины");
            } else
            if (category.equals("Accessories")) {
                return productService.getAllByCategoryAndChapter("Аксессуары", "Мужчины");
            } else
                return null;
        } else

        if (chapter.equals("Woomens")) {
            if (category.equals("T-shirt")) {
                return productService.getAllByCategoryAndChapter("Футболки", "Женщины");
            } else
            if (category.equals("Hoody")) {
                return productService.getAllByCategoryAndChapter("Худи", "Женщины");
            } else
            if (category.equals("Jacket")) {
                return productService.getAllByCategoryAndChapter("Куртки", "Женщины");
            } else
            if (category.equals("Trousers")) {
                return productService.getAllByCategoryAndChapter("Брюки", "Женщины");
            } else
            if (category.equals("Accessories")) {
                return productService.getAllByCategoryAndChapter("Аксессуары", "Женщины");
            } else
                return null;
        } else
        return null;
    }

    @GetMapping("/get_product_by_id/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }
}
