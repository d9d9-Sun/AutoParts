package by.mad.autoparts.controllers;

import by.mad.autoparts.models.Product;
import by.mad.autoparts.services.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDaoService productDaoService;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Product> productList = productDaoService.getAllProducts();
        model.addAttribute("productList", productList);
        return "catalog";
    }
}
