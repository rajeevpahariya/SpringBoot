package com.spring.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.spring.pizza.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>{
	
}
