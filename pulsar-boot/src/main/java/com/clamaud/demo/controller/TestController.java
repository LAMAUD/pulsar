package com.clamaud.demo.controller;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageBuilder;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.clamaud.demo.domain.Greeting;

@Controller
public class TestController {

	@Value(value="${spring.application.name}")
	private String value;
	
	@Autowired
	private Producer<byte[]> producer;
	
	@GetMapping("/greeting")
    public String homePage(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "home";
    }
	
	@PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) throws PulsarClientException {
		
		    Message<byte[]> msg = MessageBuilder.create()
		      .setContent(greeting.getValue().getBytes())
		      .build();
		    
		    producer.send(msg);
        return "result";
    }

}
