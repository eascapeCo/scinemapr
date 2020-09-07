package com.eascapeco.scinemapr.bo.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoCommonController {

    private final Logger log = LoggerFactory.getLogger(BoCommonController.class);

    @GetMapping("/")
    public String login(ModelMap model) {
        return "index";
    }
    
    @GetMapping("/loginForm")
    public String loginForm(ModelMap model) {
        return "index";
    }
}
