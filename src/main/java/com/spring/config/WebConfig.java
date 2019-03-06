package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		// This one is to remove the home controler, Home Controler is doing nothing so we can
		// use the WebMvcConfiguratiomn for this.
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
	}
}
