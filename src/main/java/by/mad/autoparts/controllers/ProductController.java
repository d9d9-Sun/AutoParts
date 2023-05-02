package by.mad.autoparts.controllers;

import by.mad.autoparts.models.Product;
import by.mad.autoparts.services.ProductDaoService;
import by.mad.autoparts.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDaoService productDaoService;

    private final FileSystemStorageService storageService;
    @Autowired
    public ProductController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Product> productList = productDaoService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("title", "Каталог запчастей");
        return "catalog";
    }

    @GetMapping("/admin/catalog/add-new-product")
    public String showAddNewProductForm(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("title", "Добавление нового товара");
        return "add-new-product";
    }

    @PostMapping("/admin/catalog/add-new-product")
    public String addNewProduct(@RequestParam String productVendor,
                                @RequestParam String productName,
                                @RequestParam String productDescription,
                                @RequestParam double productPrice,
                                @RequestParam("product_image") MultipartFile file,
                                @ModelAttribute("product") Product product) {


        Product newProduct = new Product(productVendor, productName, productDescription, productPrice);

        if (!file.isEmpty()) {
            storageService.store(file);
            newProduct.setProductImageLink("/product/images/" + file.getOriginalFilename());
        }

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

    @PostMapping("/admin/catalog/product-{id}/delete")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productDaoService.deleteProductById(productId);
        return "redirect:/catalog";
    }

    @PostMapping("/admin/catalog/product-{id}/delete-image")
    public String deleteProductImage(@PathVariable("id") Long productId,
                                     RedirectAttributes redirectAttributes) {
        Product product = productDaoService.findProductById(productId);
        product.setProductImageLink(null);
        productDaoService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message",
                "Фото успешно удалено!");
        return "redirect:/admin/catalog/product-{id}/edit";
    }

    @GetMapping("/admin/catalog/product-{id}/edit")
    public String productEdit(@PathVariable("id") Long productId, Model model) {
        Product product = productDaoService.findProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("title", "Редактирование товара");
        return "product-edit";
    }

    @PostMapping("/admin/catalog/product-{id}/edit")
    public String productUpdate(@PathVariable("id") Long productId,
                                @RequestParam String productVendor,
                                @RequestParam String productName,
                                @RequestParam String productDescription,
                                @RequestParam double productPrice,
                                @RequestParam("product_image") MultipartFile file,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        Product product = productDaoService.findProductById(productId);
        model.addAttribute("product", product);
        product.setProductVendor(productVendor);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);

        if (!file.isEmpty()) {
            storageService.store(file);
            product.setProductImageLink("/product/images/" + file.getOriginalFilename());
        }

        productDaoService.updateProduct(product);

        redirectAttributes.addFlashAttribute("message",
                "Товар успешно обновлён!");

        return "redirect:/catalog/product-{id}";
    }

}
