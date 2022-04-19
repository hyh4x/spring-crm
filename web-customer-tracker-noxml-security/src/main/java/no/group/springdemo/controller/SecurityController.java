package no.group.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.group.springdemo.user.CrmUser;

@Controller
public class SecurityController {
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "plain-login";
	}

}
