package com.spring.pizza.data;

import org.springframework.data.repository.CrudRepository;

import com.spring.pizza.PizzaOrder;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Long>{
	
}
