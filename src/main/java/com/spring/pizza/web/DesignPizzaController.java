package com.spring.pizza.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.pizza.Pizza;
import com.spring.pizza.PizzaIngredient;
import com.spring.pizza.PizzaIngredient.Type;
import com.spring.pizza.PizzaOrder;
import com.spring.pizza.data.PizzaIngredientRepository;
import com.spring.pizza.data.PizzaRepository;


@Component
@RequestMapping("/pizzaDesign")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
	
	@Autowired
	PizzaIngredientRepository pizzIngRepo;
	
	@Autowired
	PizzaRepository pizzaRepo;
	
	@ModelAttribute("pizzaOrder")
	public PizzaOrder getOrder(){
		return new PizzaOrder();
	}
	
	@ModelAttribute("pizza")
	public Pizza getPizza(){
		return new Pizza();
	}
	
	@GetMapping
	public String showDesign(Model pModel) {
		List<PizzaIngredient> findAll = pizzIngRepo.findAll();
		Type[] values = PizzaIngredient.Type.values();
		for(Type type : values) {
			pModel.addAttribute(type.toString(),filterByType(type,findAll));
		}
		return "pizzaDesign";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("pizza") Pizza pizza, Errors error,
			Model model, @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {
		if(error.hasErrors()) {
			return "pizzaDesign";
		}
		pizzaRepo.save(pizza);
		pizzaOrder.addPizza(pizza);
		return "redirect:/pizzaOrder/current";
	}

	private List<PizzaIngredient> filterByType(Type type, List<PizzaIngredient> findAll) {
		return findAll.stream().filter(ing -> ing.getType().equals(type)).collect(Collectors.toList());
	}

}
