package com.example.simpleecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.simpleecommerceapp.entity.Admin;
import com.example.simpleecommerceapp.entity.User;
import com.example.simpleecommerceapp.service.AdminService;
import com.example.simpleecommerceapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "LoginPage";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@ModelAttribute("admin") Admin admin, RedirectAttributes redirectAttributes, HttpSession session) {
        if (adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
            session.setAttribute("admin", admin);
            return "redirect:/admin/home";
        }

        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/login";
    }

    @PostMapping("/user/login")
    public String userLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session) {
        if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
            user = userService.findUserByEmail(user.getEmail());
            session.setAttribute("user", user);
            return "redirect:/user/home";
        }

        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "RegisterPage";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("messageSuccess", "Registration successful! Please log in.");
        return "redirect:/login";
    }
}