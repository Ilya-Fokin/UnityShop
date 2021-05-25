package org.example.Repository;

import org.example.Domains.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepo extends CrudRepository<Size, Long> {
    Size findByName(String name);
}
