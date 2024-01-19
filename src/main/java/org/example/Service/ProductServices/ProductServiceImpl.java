package org.example.Service.ProductServices;

import org.example.Domains.Basket;
import org.example.Domains.Order;
import org.example.Domains.Product;
import org.example.Domains.ProductSize;
import org.example.Repository.*;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private BasketRepo basketRepo;

    @Autowired
    private OrderRepo orderRepo;

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
        return false;
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
    public String deleteProduct(Long id) {
         Product product = productRepo.findById(id).get();
        if (product != null) {
            List<Basket> baskets = basketRepo.findAllByProductId(id);
            if (baskets != null) {
                for (Basket basket : baskets) {
                    List<Order> orders = orderRepo.findAllByBasketId(basket.getId());
                    if (orders != null) {
                        for (Order order : orders) {
                            orderRepo.delete(order);
                        }
                        basketRepo.delete(basket);
                    } else
                    basketRepo.delete(basket);
                }
                productRepo.delete(product);
            } else
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

    @Override
    public Product getProductById(Long id) {
         if (productRepo.findById(id).isPresent()) {
             Product product = productRepo.findById(id).get();
             return product;
         }  else
        return null;
    }

    @Override
    public Boolean findById(Long id) {
         if (productRepo.findById(id).isPresent()) {
             return true;
         } else
        return null;
    }

    @Override
    public Product findByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> findAllByName(String name) {
         if (productRepo.findAllByName(name) != null)
         {
             return productRepo.findAllByName(name);
         }
        return null;
    }

    @Override
    public List<Product> findAllByNameContaining(String name) {
        return productRepo.findAllByNameContaining(name);
    }

    @Override
    public Boolean editProduct(Long id, String name, String description, int price) {
        if (findById(id)) {
            Product product = productRepo.findById(id).get();
            if (name != null) {
                product.setName(name);
                if (description != null) {
                    product.setDescription(description);
                    if (price != 0) {
                        product.setPrice(price);
                    }
                }
            } else

            if (description != null) {
                product.setDescription(description);
                if (price != 0) {
                    product.setPrice(price);
                }
            } else

            if (price != 0) {
                product.setPrice(price);
            }
            productRepo.save(product);
            return true;
        } else return false;
    }
}
