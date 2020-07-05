package com.eascapeco.scinemapr.bo.controller.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.eascapeco.scinemapr.api.model.Result;

@Controller
public class ExceptionController implements ErrorController {
 
    @GetMapping(value = "/entrypoint")
    public Result entrypointException() {
        System.out.println("tetetet");
        throw new RuntimeException();
    }

    @GetMapping("/error")
    public String redirect() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}