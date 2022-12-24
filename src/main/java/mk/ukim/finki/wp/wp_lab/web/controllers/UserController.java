package mk.ukim.finki.wp.wp_lab.web.controllers;

import mk.ukim.finki.wp.wp_lab.model.Role;
import mk.ukim.finki.wp.wp_lab.service.UserService;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String getRegisterPage()
    {
        return "register";
    }
    @PostMapping
    @Transactional
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role
    )
    {

        try{
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            System.out.println(userService.findAll());
            return "redirect:/login";
        } catch (InvalidArgumentException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }

}
