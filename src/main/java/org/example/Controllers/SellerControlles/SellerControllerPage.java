package org.example.Controllers.SellerControlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerControllerPage {
    @GetMapping("/seller_account/add_product")
    public String addProductSeller() {
        return "add_product";
    }

    @GetMapping("/seller_account/delete_product")
    public String deleteProductSeller() {
        return "delete_product";
    }
}
