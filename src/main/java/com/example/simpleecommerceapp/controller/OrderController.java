package com.example.simpleecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.simpleecommerceapp.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	
}
