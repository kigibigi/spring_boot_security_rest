package bm.com.spring_mvc_boot.controllers;

import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/users/index";
    }

    @GetMapping("/person")
    public String show(@RequestParam(value = "id") Long id,
                       Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute User user) {
        return "/users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/update/person")
    public String edit(@RequestParam(name = "id") Long id,
                         Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PatchMapping("/person")
    public String update(@ModelAttribute User user,
                         @RequestParam(name = "id") Long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/person")
    public String delete(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
