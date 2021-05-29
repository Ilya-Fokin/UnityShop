package org.example.Service.ProductServices;

import org.example.Domains.Product;

import java.util.List;

public interface ProductService {
    Boolean createProduct(String name, String description, String image, int price, String category, String chapter);
    Boolean checkProduct(String name);
    Long getProductId(String name);
    String deleteProduct(String name);
    List<Product> getAllByCategoryAndChapter(String category, String chapter);
    Product findByCategoryAndChapter(String category, String chapter);
    Product getProductById(Long id);
    Boolean findById(Long id);
    Product findByName(String name);
    List<Product> findAllByName(String name);
    List<Product> findAllByNameContaining(String name);
    Boolean editProduct(Long id, String name, String description, int price);
}
