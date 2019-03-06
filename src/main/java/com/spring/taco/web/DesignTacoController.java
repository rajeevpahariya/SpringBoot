package com.spring.taco.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.taco.Ingredient;
import com.spring.taco.Ingredient.Type;
import com.spring.taco.Order;
import com.spring.taco.Taco;
import com.spring.taco.data.JdbcIngredientRepository;
import com.spring.taco.data.JdbcTacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	// Either use below line or we can use the @Slf4j annotation.
	//private static final org.slf4j.Logger log =
	//	    org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	private JdbcTacoRepository tacoRepo;
	private JdbcIngredientRepository ingRepo;
	
	@Autowired
	public DesignTacoController(JdbcTacoRepository tacoRepo, JdbcIngredientRepository ingRepo) {
		super();
		this.tacoRepo = tacoRepo;
		this.ingRepo = ingRepo;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model pModel) {
//		List<Ingredient> ingredients = Arrays.asList(
//			    new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//			    new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//			    new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//			    new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//			    new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//			    new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//			    new Ingredient("CHED", "Cheddar", Type.CHEESE),
//			    new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//			    new Ingredient("SLSA", "Salsa", Type.SAUCE),
//			    new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//			);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingRepo.findAll().forEach(i->ingredients.add(i));
		Type[] values = Ingredient.Type.values();
		for(Type lType : values) {
			pModel.addAttribute(lType.toString(), filterByType(ingredients,lType));
		}
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model pModel) {
		//pModel.addAttribute("design", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors error, Model pModel
			, @ModelAttribute Order order) {
		if(error.hasErrors()) {
			return "design";
		}
		log.info("pTaco ::::: "+design);
//		for(String t : design.getIngredients()) {
//			log.info("Ing ::::: "+t);
//		}
		// Below code will start to persist the data in repository
		Taco save = tacoRepo.save(design);
		order.addDesign(save);
		return "redirect:/orders/current";
		
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type lType) {
		return ingredients.
					stream().
					filter(x -> x.getType().equals(lType)).
					collect(Collectors.toList());
	}
}
