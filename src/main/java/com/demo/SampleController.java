package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class SampleController {
	
	@RequestMapping("/home")
	String home(){
		return "hello";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SampleController.class, args);
	}

}
