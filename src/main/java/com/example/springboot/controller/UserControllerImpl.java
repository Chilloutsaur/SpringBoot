package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserControllerImpl {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users";
    }

    @GetMapping("/{id}/edit")
    public String getEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String updateUser( @PathVariable("id") Long id, @ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "form";
        userService.updateUser(id, user);
        return "redirect:/users";
    }


}
