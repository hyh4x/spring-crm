package no.group.springdemo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import no.group.springdemo.entity.User;
import no.group.springdemo.service.UserService;
import no.group.springdemo.user.CrmUser;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		
		model.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
					@Valid @ModelAttribute("crmUser") CrmUser crmUser,
					BindingResult bindingResult,
					Model model) {
		
		String username = crmUser.getUsername();
		// Checking for validation errors
		if(bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getFieldErrors()
					.stream()
					.map(err -> {
						return err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			model.addAttribute("registrationError", errorMessages);
				return "registration-form";
		}
		
		// Checking if user already exists
		User existing = userService.findByUsername(username);
		if(existing!=null) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "Username already exists.");
			
			return "registration-form";
		}
		
		userService.save(crmUser);
		
		return "registration-confirmation";
	}
}
