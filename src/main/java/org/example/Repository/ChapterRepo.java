package org.example.Repository;

import org.example.Domains.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepo extends CrudRepository<Chapter, Long> {
    Chapter findByName(String name);
}
