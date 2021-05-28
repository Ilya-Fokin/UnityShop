package org.example.Service.FavoritesService;

import org.example.Domains.Favorites;

import java.util.List;

public interface FavoritesService {
    List<Favorites> findAll(Long userId, Long productId);
    Boolean addFavorite(Long userId, Long productId);
    Boolean checkFavorite(Long userId, Long productId);
}
