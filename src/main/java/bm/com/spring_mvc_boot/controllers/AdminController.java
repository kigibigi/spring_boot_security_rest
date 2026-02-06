package bm.com.spring_mvc_boot.controllers;

import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/index";
    }

    @GetMapping("/new")
    public String getPageCreateUser(@ModelAttribute User user) {
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit";
    }

    @PatchMapping()
    public String updateUser(@ModelAttribute User user, @RequestParam(name = "id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping()
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
