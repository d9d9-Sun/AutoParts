package by.mad.autoparts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    private String homePage(Model model) {
        model.addAttribute("title", "Auto Parts Store");
        return "index";
    }
}
