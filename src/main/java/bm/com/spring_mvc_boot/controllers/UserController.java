package bm.com.spring_mvc_boot.controllers;

import bm.com.spring_mvc_boot.security.UserDetailsImpl;
import bm.com.spring_mvc_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(@RequestParam(value = "id") Long id,
                       Model model) {
        model.addAttribute("user", userService.findById(id));

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        System.out.println(authentication.getAuthorities());
//        System.out.println(userDetails.getUser().getRoles());

        return "users/show";
    }

}
