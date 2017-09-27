package com.qht.boot.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// If themeleaf or other templates you can include it in template folder
// 
@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello(Model model) {
	
		String str = new String("Rey's String");
		model.addAttribute("key", str);
		
		return "hello";
	}
}
