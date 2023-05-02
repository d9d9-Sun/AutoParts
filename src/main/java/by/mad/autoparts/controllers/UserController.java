package by.mad.autoparts.controllers;

import by.mad.autoparts.models.Users;
import by.mad.autoparts.repositories.UserRepository;
import by.mad.autoparts.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(Users user,
                             Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("message", "Такой логин уже есть в системе.");
            return "registration";
        }
        if (userService.createUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Неверный ввод");
        }

        return "registration";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
