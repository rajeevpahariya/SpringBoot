package com.spring.pizza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "pizza_order")
public class PizzaOrder {
	// end::allButValidation[]
	@NotBlank(message = "Name is required")
	// tag::allButValidation[]
	private String name;
	// end::allButValidation[]

	@NotBlank(message = "Street is required")
	// tag::allButValidation[]
	private String street;
	// end::allButValidation[]

	@NotBlank(message = "City is required")
	// tag::allButValidation[]
	private String city;
	// end::allButValidation[]

	@NotBlank(message = "State is required")
	// tag::allButValidation[]
	private String state;
	// end::allButValidation[]

	@NotBlank(message = "Zip code is required")
	// tag::allButValidation[]
	private String zip;
	// end::allButValidation[]

	@CreditCardNumber(message = "Not a valid credit card number")
	// tag::allButValidation[]
	private String ccNumber;
	// end::allButValidation[]

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	// tag::allButValidation[]
	private String ccExpiration;
	// end::allButValidation[]

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	// tag::allButValidation[]
	private String ccCVV;

	// Below properties added for JDBC Template purpose.
	// id -> Unique identifier
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date createdAt;

	@ManyToMany(targetEntity=Pizza.class)
	private List<Pizza> pizza = new ArrayList<>();

	public void addPizza(Pizza design) {
		this.pizza.add(design);
	}

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
