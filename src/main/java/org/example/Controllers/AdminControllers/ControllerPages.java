package org.example.Controllers.AdminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerPages {
    @GetMapping("/admin_account/add_product")
    public String addProduct() {
        return "add_product";
    }

    @GetMapping("/admin_account/delete_product")
    public String deleteProduct() {
        return "delete_product";
    }

    @GetMapping("/admin_account/edit_product")
    public String editProductPage() {
        return "search_edit_product";
    }

}
