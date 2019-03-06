package com.spring.taco.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.taco.Order;
import com.spring.taco.data.JdbcOrderRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private JdbcOrderRepository orderRepo;
	
	@Autowired
	public OrderController(JdbcOrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		//model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors error,
			SessionStatus status) {
		if (error.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: " + order);
		orderRepo.save(order);
		status.setComplete();
		return "redirect:/";
	}
}
