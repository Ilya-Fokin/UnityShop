package org.example.Service.ProductSizeService;

import org.example.Domains.ProductSize;
import org.springframework.stereotype.Service;

public interface ProductSizeService {
    Boolean createProductSize(Long productID, Long sizeID, int count);
    Iterable<ProductSize> findAllByProductId(Long id);
    void deleteProductSize(ProductSize productSize);
}
