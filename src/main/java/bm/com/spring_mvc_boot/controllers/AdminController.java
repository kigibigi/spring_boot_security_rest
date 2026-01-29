package bm.com.spring_mvc_boot.controllers;

import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute User user) {
        return "/admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String edit(@RequestParam(name = "id") Long id,
                       Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit";
    }

    @PatchMapping()
    public String update(@ModelAttribute User user,
                         @RequestParam(name = "id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping()
    public String delete(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
