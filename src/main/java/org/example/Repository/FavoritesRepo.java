package org.example.Repository;

import org.example.Domains.Favorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepo extends CrudRepository<Favorites, Long> {
    List<Favorites> findAllByUserIdAndProductId(Long userId, Long productId);
    Favorites findByUserIdAndProductId(Long userId, Long productId);
    List<Favorites> findAllByUserId(Long userId);
}
