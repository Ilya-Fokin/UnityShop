package org.example.Repository;

import org.example.Domains.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
