package org.example.Service.ProductSizeService;

import org.example.Domains.ProductSize;
import org.example.Repository.ProductSizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSizeServiceImpl implements ProductSizeService{
    @Autowired
    private ProductSizeRepo productSizeRepo;

    @Override
    public Boolean createProductSize(Long productID, Long sizeID, int count) {
        ProductSize productSize = new ProductSize();
        productSize.setProductID(productID);
        productSize.setSizeID(sizeID);
        productSize.setCount(count);
        productSizeRepo.save(productSize);
        return null;
    }

    @Override
    public Iterable<ProductSize> findAllByProductId(Long id) {
        return productSizeRepo.findAllByProductID(id);
    }

    @Override
    public void deleteProductSize(ProductSize productSize) {
        productSizeRepo.delete(productSize);
    }
}
