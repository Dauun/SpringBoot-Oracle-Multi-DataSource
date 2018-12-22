package com.test.app.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String login(Principal principal) {
		if (principal == null) {
			return "login/login";
		}
		return "redirect:/";
	}
}