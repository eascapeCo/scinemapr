package com.eascapeco.scinemapr.bo.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * UserDetailsService 구현체
 * 
 * @date 2019. 11. 01
 * @author jaehankim
 *
 */

@RestController
public class BoAuthController {

	private final Logger log = LoggerFactory.getLogger(BoAuthController.class);

	@PostMapping("/api/admin/login")
	public String login(String id, String pwd) {
		System.out.println(111);
		log.debug("id {}", id);
		log.debug("pwd {}", pwd);
		return "index";
	}
}
