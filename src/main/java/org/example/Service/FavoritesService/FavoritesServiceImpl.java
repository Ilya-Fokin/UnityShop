package org.example.Service.FavoritesService;

import org.example.Domains.Favorites;
import org.example.Repository.FavoritesRepo;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService{
    @Autowired
    private FavoritesRepo favoritesRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Favorites> findAll(Long userId, Long productId) {
        return favoritesRepo.findAllByUserIdAndProductId(userId, productId);
    }

    @Override
    public Boolean checkFavorite(Long userId, Long productId) {
        if (favoritesRepo.findByUserIdAndProductId(userId, productId) == null) {
            return false;
        } else return true;
    }

    @Override
    public Boolean addFavorite(Long userId, Long productId) {
        if (userService.findById(userId)) {
            if (productService.findById(productId)) {
                if (!checkFavorite(userId, productId)) {
                    Favorites favorites = new Favorites();
                    favorites.setUserId(userId);
                    favorites.setProductId(productId);
                    favoritesRepo.save(favorites);

                    return true;
                } else return false;
            } else return false;
        } else return false;
    }
}
