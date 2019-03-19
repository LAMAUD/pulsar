package com.clamaud.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@Value(value="${spring.application.name}")
	private String value;
	
	
	@GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", value);
        return "home";
    }

}
