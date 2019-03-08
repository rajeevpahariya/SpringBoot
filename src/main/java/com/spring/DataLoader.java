package com.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.pizza.PizzaIngredient;
import com.spring.pizza.PizzaIngredient.Type;
import com.spring.pizza.data.PizzaIngredientRepository;

@Component
public class DataLoader {
	
	@Autowired
	private PizzaIngredientRepository pizzaIngRepo;
	
	@PostConstruct
	private void loadData() {
		PizzaIngredient ing1 = new PizzaIngredient(Type.WRAP);
		ing1.setId("FLTO");
		ing1.setName("Flour Tortilla");
		pizzaIngRepo.save(ing1);
		
		PizzaIngredient ing2 = new PizzaIngredient(Type.WRAP);
		ing2.setId("COTO");
		ing2.setName("Corn Tortilla");
		pizzaIngRepo.save(ing2);
		
		PizzaIngredient ing3 = new PizzaIngredient(Type.PROTEIN);
		ing3.setId("GRBF");
		ing3.setName("Ground Beef");
		pizzaIngRepo.save(ing3);
		
		PizzaIngredient ing4 = new PizzaIngredient(Type.PROTEIN);
		ing4.setId("CARN");
		ing4.setName("Carnitas");
		pizzaIngRepo.save(ing4);
		
		PizzaIngredient ing5 = new PizzaIngredient(Type.VEGGIES);
		ing5.setId("TMTO");
		ing5.setName("Diced Tomatoes");
		pizzaIngRepo.save(ing5);
		
		PizzaIngredient ing6 = new PizzaIngredient(Type.VEGGIES);
		ing6.setId("LETC");
		ing6.setName("Lettuce");
		pizzaIngRepo.save(ing6);
		
		PizzaIngredient ing7 = new PizzaIngredient(Type.CHEESE);
		ing7.setId("CHED");
		ing7.setName("Cheddar");
		pizzaIngRepo.save(ing7);
		
		PizzaIngredient ing8 = new PizzaIngredient(Type.CHEESE);
		ing8.setId("JACK");
		ing8.setName("Monterrey Jack");
		pizzaIngRepo.save(ing8);
		
		PizzaIngredient ing9 = new PizzaIngredient(Type.SAUCE);
		ing9.setId("SLSA");
		ing9.setName("Salsa");
		pizzaIngRepo.save(ing9);
		
		PizzaIngredient ing10 = new PizzaIngredient(Type.SAUCE);
		ing10.setId("SRCR");
		ing10.setName("Sour Cream");
		pizzaIngRepo.save(ing10);
	}
}
