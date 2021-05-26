package org.example.Service.ProductSizeService;

import org.example.Domains.Product;
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

    @Override
    public Boolean reduceProductCount(Long productId, Long sizeId) {
        ProductSize productSize = productSizeRepo.findByProductIDAndSizeID(productId, sizeId);
        if (productSize != null) {
            int count = productSize.getCount();

            if (count != 0) {
                count = count-1;

                if (count == 0) {
                    productSizeRepo.delete(productSize);
                    return true;
                } else
                    productSize.setCount(count);
                    productSizeRepo.save(productSize);
                return true;
            } else return false;
        } return false;
    }

    @Override
    public Boolean addProductCount(Long productId, Long sizeId) {
        ProductSize productSize = productSizeRepo.findByProductIDAndSizeID(productId, sizeId);
        int count = 0;
        if (productSize == null) {
            createProductSize(productId, sizeId, 1);
            return true;
        } else
        count++;
        productSize.setCount(count);
        productSizeRepo.save(productSize);
        return true;
    }

    @Override
    public ProductSize findByProductIDAndSizeID(Long productId, Long sizeId) {
        ProductSize productSize = productSizeRepo.findByProductIDAndSizeID(productId, sizeId);


        if (productSize != null) {
            return productSize;
        } else return null;
    }
}


