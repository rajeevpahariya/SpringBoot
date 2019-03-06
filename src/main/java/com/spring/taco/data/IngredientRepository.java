package com.spring.taco.data;

import com.spring.taco.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String id);
	
	Ingredient save(Ingredient ingredient);
}
