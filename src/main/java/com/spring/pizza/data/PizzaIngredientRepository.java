package com.spring.pizza.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.pizza.PizzaIngredient;

public interface PizzaIngredientRepository extends CrudRepository<PizzaIngredient, String>{
	List<PizzaIngredient> findAll();
}
