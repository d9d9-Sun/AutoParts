package by.mad.autoparts.controllers;

import by.mad.autoparts.models.Product;
import by.mad.autoparts.services.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDaoService productDaoService;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Product> productList = productDaoService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("title", "Каталог запчастей");
        return "catalog";
    }

    @GetMapping("/catalog/add-new-product")
    public String showAddNewProductForm(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("title", "Добавление нового товара");
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

    @GetMapping("/catalog/product-{id}")
    public String showSingleProduct(@PathVariable("id") Long productId, Model model) {
        Product product = productDaoService.findProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("title", product.getProductName());
        return "product";
    }

    @PostMapping("/catalog/product-{id}/delete")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productDaoService.deleteProductById(productId);
        return "redirect:/catalog";
    }

    @GetMapping("/catalog/product-{id}/edit")
    public String productEdit(@PathVariable("id") Long productId, Model model) {
        Product product = productDaoService.findProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("title", "Редактирование товара");
        return "product-edit";
    }

    @PostMapping("/catalog/product-{id}/edit")
    public String productUpdate(@PathVariable("id") Long productId,
                                @RequestParam String productVendor,
                                @RequestParam String productName,
                                @RequestParam String productDescription,
                                @RequestParam double productPrice,
                                Model model) {

        Product product = productDaoService.findProductById(productId);
        product.setProductVendor(productVendor);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);

        productDaoService.updateProduct(product);

        return "redirect:/catalog/product-{id}";
    }
}
