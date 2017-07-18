package com.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping(value={"/"})
	public String home(HttpServletRequest req){
		System.out.println(req.getServletContext().getRealPath(""));
		return "index";
	}

}
