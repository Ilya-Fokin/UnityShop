package org.example.Service.ProductServices;

import org.example.Domains.Product;
import org.example.Domains.ProductSize;
import org.example.Repository.CategoryRepo;
import org.example.Repository.ChapterRepo;
import org.example.Repository.ProductRepo;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ChapterRepo chapterRepo;

    @Autowired
    private ProductSizeService productSizeService;

     @Override
    public Boolean createProduct(String name, String description, String image, int price, String category, String chapter) {
        if (!checkProduct(name)) {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImage(image);
            product.setPrice(price);
            product.setCategory(category);
            product.setChapter(chapter);

            productRepo.save(product);

            return true;
        }
        return null;
    }

    @Override
    public Boolean checkProduct(String name) {
        Product product = productRepo.findByName(name);
        if (product != null) {
            return true;
        } else return false;
    }

    @Override
    public Long getProductId(String name) {
         if (checkProduct(name)) {
             Product product = productRepo.findByName(name);
             Long id = product.getId();
             return id;
         } else return null;
    }

    @Override
    public String deleteProduct(String name) {
        if (checkProduct(name)) {
            Product product = productRepo.findByName(name);
            Long id = product.getId();

            Iterable<ProductSize> productSizes = productSizeService.findAllByProductId(id);

            for (ProductSize productSize : productSizes) {
                productSizeService.deleteProductSize(productSize);
            }
                productRepo.delete(product);
                return "Товар удален";

        } return "Товар не найден";
    }

    @Override
    public List<Product> getAllByCategoryAndChapter(String category, String chapter) {
         List<Product> products = productRepo.findAllByCategoryAndChapter(category,chapter);
         if (products != null) {
             return products;
         } else return null;
    }

    @Override
    public Product findByCategoryAndChapter(String category, String chapter) {
        return productRepo.findByCategoryAndChapter(category, chapter);
    }
}
