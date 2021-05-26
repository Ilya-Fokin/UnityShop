package org.example.Repository;

import org.example.Domains.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAllByCategoryAndChapter(String category, String chapter);
    Product findByCategoryAndChapter(String category, String chapter);
    Optional<Product> findById(Long id);
}
