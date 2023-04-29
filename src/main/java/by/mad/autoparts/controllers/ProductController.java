package by.mad.autoparts.controllers;

import by.mad.autoparts.models.Product;
import by.mad.autoparts.services.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/catalog/add-new-product")
    public String showAddNewProductForm(@ModelAttribute("product") Product product) {
        return "add-new-product";
    }

    @PostMapping("/catalog/add-new-product")
    public String addNewProduct(@RequestParam String productVendor,
                                @RequestParam String productName,
                                @RequestParam String productDescription,
                                @RequestParam double productPrice,
                                @ModelAttribute("product") Product product) {

        Product newProduct = new Product(productVendor, productName, productDescription, productPrice);
        productDaoService.addNewProduct(newProduct);
        return "redirect:/catalog";
    }
}
