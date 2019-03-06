package com.spring.pizza;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Pizza {
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@ManyToMany(targetEntity = PizzaIngredient.class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<PizzaIngredient> pizzaIngredients;

	// Below properties added for JDBC Template purpose.
	// id -> Unique identifier
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date createdAt;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
