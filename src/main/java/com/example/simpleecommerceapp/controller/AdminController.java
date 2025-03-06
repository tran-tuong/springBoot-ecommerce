package com.example.simpleecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.simpleecommerceapp.entity.Admin;
import com.example.simpleecommerceapp.service.AdminService;
import com.example.simpleecommerceapp.service.OrderService;
import com.example.simpleecommerceapp.service.ProductService;
import com.example.simpleecommerceapp.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping("/admin/home")
	public String adminHomePage(Model model) {
		model.addAttribute("adminList", adminService.getAllAdmin());
		model.addAttribute("userList", userService.getAllUser());
		model.addAttribute("orderList", orderService.getAllOrder());
		model.addAttribute("productList", productService.getAllProduct());

		return "AdminHomePage";
	}

	@PostMapping("/add/admin")
	public String createAdmin(Admin admin) {
		adminService.createUser(admin);
		return "redirect:/admin/home";
	}

	@GetMapping("/update/admin/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("admin", adminService.getAdminById(id));
		return "UpdateAdmin";
	}

	@PostMapping("/update/admin")
	public String updateAdmin(Admin admin) {
		adminService.updateAdmin(admin);
		return "redirect:/admin/home";
	}

	@GetMapping("/delete/admin/{id}")
	public String deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		return "redirect:/admin/home";
	}
}
