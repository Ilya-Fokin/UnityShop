package org.example.Controllers.EditProductController;

import org.example.Domains.Product;
import org.example.Domains.ProductSize;
import org.example.Service.BasketService.BasketService;
import org.example.Service.ProductServices.ProductService;
import org.example.Service.ProductSizeService.ProductSizeService;
import org.example.Service.SizeService.SizeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private SizeService sizeService;

    @PostMapping("/add_product_item")
    public String addProduct(@RequestBody String product) throws JSONException {
        Long productId = null;
        Long sizeId = null;

        JSONObject jsonObject = new JSONObject(product);

        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        String chapter = jsonObject.getString("chapter");
        String description = jsonObject.getString("description");
        String image = jsonObject.getString("image");
        int price = jsonObject.getInt("price");

        int countXS = jsonObject.getInt("XS");
        int countS = jsonObject.getInt("S");
        int countM = jsonObject.getInt("M");
        int countL = jsonObject.getInt("L");
        int countXL = jsonObject.getInt("XL");

        if (productService.createProduct(name, description, image, price, category, chapter)) {
            if (productService.getProductId(name) != null) {
                productId = productService.getProductId(name);

                if (countXS != 0) {
                    sizeId = sizeService.getSizeId("XS");
                    productSizeService.createProductSize(productId, sizeId, countXS);
                }
                if (countS != 0) {
                    sizeId = sizeService.getSizeId("S");
                    productSizeService.createProductSize(productId, sizeId, countS);
                }
                if (countM != 0) {
                    sizeId = sizeService.getSizeId("M");
                    productSizeService.createProductSize(productId, sizeId, countM);
                }
                if (countL != 0) {
                    sizeId = sizeService.getSizeId("L");
                    productSizeService.createProductSize(productId, sizeId, countL);
                }
                if (countXL != 0) {
                    sizeId = sizeService.getSizeId("XL");
                    productSizeService.createProductSize(productId, sizeId, countXL);
                }

                return "Товар добавлен";
            } else return "Не удалось найти идентификатор товара";
        } else return null;
    }

    @PostMapping("/delete_product_item/{name}")
    public String deleteProduct(@PathVariable(name = "name") String nameProduct) {
        return productService.deleteProduct(nameProduct);
    }

    @PostMapping("/edit_product/{name}/")
    public String editProduct(@PathVariable(name = "name") String name) {
        if (productService.findByName(name) != null) {
            return "rbreb";
        } else return null;
    }

    @PostMapping("/edit_product_item")
    public String editProductItem(@RequestBody String response) throws JSONException {
        Long sizeId = null;
        JSONObject jsonObject = new JSONObject(response);

        Long productId = jsonObject.getLong("id");
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int price = jsonObject.getInt("price");

        int countXS = jsonObject.getInt("XS");
        int countS = jsonObject.getInt("S");
        int countM = jsonObject.getInt("M");
        int countL = jsonObject.getInt("L");
        int countXL = jsonObject.getInt("XL");

        if (productService.editProduct(productId, name, description, price)) {
            if (productService.getProductId(name) != null) {
                productId = productService.getProductId(name);

                if (countXS != 0) {
                    sizeId = sizeService.getSizeId("XS");
                    productSizeService.createProductSize(productId, sizeId, countXS);
                }
                if (countS != 0) {
                    sizeId = sizeService.getSizeId("S");
                    productSizeService.createProductSize(productId, sizeId, countS);
                }
                if (countM != 0) {
                    sizeId = sizeService.getSizeId("M");
                    productSizeService.createProductSize(productId, sizeId, countM);
                }
                if (countL != 0) {
                    sizeId = sizeService.getSizeId("L");
                    productSizeService.createProductSize(productId, sizeId, countL);
                }
                if (countXL != 0) {
                    sizeId = sizeService.getSizeId("XL");
                    productSizeService.createProductSize(productId, sizeId, countXL);
                }

                return "Товар изменен";
            } else return "Не удалось найти идентификатор товара";
        } else return "не удалось изменить данные";
    }
}
