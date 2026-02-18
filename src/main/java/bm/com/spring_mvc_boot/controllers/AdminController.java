package bm.com.spring_mvc_boot.controllers;

import bm.com.spring_mvc_boot.model.Role;
import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.security.UserDetailsImpl;
import bm.com.spring_mvc_boot.service.RoleService;
import bm.com.spring_mvc_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("admin", getUserDetailsFromPrincipal().getUser());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/index";
    }

    @GetMapping("/new")
    public String getPageCreateUser(@ModelAttribute User user, Model model) {
        model.addAttribute("admin", getUserDetailsFromPrincipal().getUser());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute User user, @RequestParam("role") String role) {
        userService.save(user, role);
        return "redirect:/admin";
    }

//    @GetMapping("/update")
//    public String editUser(@RequestParam(name = "id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "admin/edit";
//    }

    @PatchMapping()
    public String updateUser(@ModelAttribute User user, @RequestParam(name = "id") Long id, @RequestParam("role") String role) {
        userService.update(id, user, role);
        return "redirect:/admin";
    }

    @DeleteMapping()
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    private static UserDetailsImpl getUserDetailsFromPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }
}
