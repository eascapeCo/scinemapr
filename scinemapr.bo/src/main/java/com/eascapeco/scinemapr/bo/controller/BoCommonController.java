package com.eascapeco.scinemapr.bo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoCommonController {

	@GetMapping("/test")
	public String login(ModelMap model) {
		System.out.println(1);
		return "test";
	}
}
