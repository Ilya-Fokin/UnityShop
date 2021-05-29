package org.example.Controllers.EditProductController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditPageController {

    @GetMapping("/edit_product_page/{productId}")
    public String editPage(@PathVariable(name = "productId") Long productId) {
        return "edit_product_page";
    }
}
