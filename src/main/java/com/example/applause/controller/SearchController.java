package com.example.applause.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.applause.entity.Device;
import com.example.applause.entity.Output;
import com.example.applause.entity.SearchCriteria;
import com.example.applause.service.Service;

@Controller
public class SearchController {

//@Autowired
private Service service = new Service();

@GetMapping("/")
public String main(Model model) {	
	List<String> allCountry = service.getAllCountry();	
	List<Device> allDevice = service.getAllDevice();
	model.addAttribute("searchCriteria", new SearchCriteria());
	model.addAttribute("allCountry", allCountry);	
	model.addAttribute("allDevice", allDevice);
	return "index";
}

@PostMapping("/")
public String Submit(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, BindingResult bindingResult,  Model model, RedirectAttributes ra) {
	
	if ( bindingResult.hasErrors() ) {
		return "/";
	}
	ra.addFlashAttribute("searchCriteria", searchCriteria);
	
	return "redirect:/result";
}

@GetMapping("/result")
public String fooresult(
		@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
		Model model) {
	List<Output> output = service.Search(searchCriteria);
	model.addAttribute("output", output);
	return "result";
}
}

