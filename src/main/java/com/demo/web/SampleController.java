package com.demo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	
	@GetMapping(value={"/","/home"})
	public String home(HttpServletRequest req,Map<String,String> model){
		System.out.println(req.getServletContext().getRealPath(""));
		req.setAttribute("sessionId", req.getSession().getId());
		model.put("encoding", req.getCharacterEncoding());
		model.put("user", "韩菱纱");
		return "index";
	}
	
	@RequestMapping(value="/redis")
	public String redisTest(){
		return "redisTest";
	}

}
