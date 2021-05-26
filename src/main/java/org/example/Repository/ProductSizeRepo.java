package org.example.Repository;

import org.example.Domains.ProductSize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductSizeRepo extends CrudRepository<ProductSize, Long> {
    Iterable<ProductSize> findAllByProductID(Long id);
    ProductSize findByProductIDAndSizeID(Long productId, Long sizeId);
}
