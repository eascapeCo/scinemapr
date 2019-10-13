package com.eascapeco.scinemapr.bo.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.Result;
import com.eascapeco.scinemapr.api.service.admin.AdminService;

@Controller
public class BoCommonController {

	private final Logger log = LoggerFactory.getLogger(BoCommonController.class);

	@Autowired
	AdminService adminService;
	
	@GetMapping("/")
	public String login(ModelMap model) {
		Admin admin = new Admin();
		admin.setId("admin");
		Result re = adminService.adminLogin(admin);
		
		log.debug("pwd {}", re.getMessage());
		
		return "index";
	}
}
