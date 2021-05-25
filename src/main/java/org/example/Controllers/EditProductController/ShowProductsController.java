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

        return productService.getAllByCategoryAndChapter(category, chapter);
    }
}
