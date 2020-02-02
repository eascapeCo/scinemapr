package com.eascapeco.scinemapr.bo.controller.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eascapeco.scinemapr.api.model.Result;

@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {
 
    @GetMapping(value = "/entrypoint")
    public Result entrypointException() {
        System.out.println("tetetet");
        throw new RuntimeException();
    }
}