package com.spring.pizza.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.pizza.PizzaOrder;
import com.spring.pizza.data.PizzaOrderRepository;

@Component
@RequestMapping("/pizzaOrder")
@SessionAttributes("pizzaOrder")
public class PizzaOrderController {
	
	@Autowired
	PizzaOrderRepository pizzOrderRepo;
	
	@GetMapping("current")
	public String orderForm(Model model) {
		return "pizzaOrderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid PizzaOrder order, Errors error,
			SessionStatus status) {
		if(error.hasErrors()) {
			return "pizzaOrderForm";
		}
		PizzaOrder save = pizzOrderRepo.save(order);
		System.out.println("ID ===================   "+save.getId());
		status.setComplete();
		return "redirect:/";
		
	}
}
