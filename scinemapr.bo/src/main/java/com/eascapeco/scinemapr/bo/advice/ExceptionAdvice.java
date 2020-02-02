package com.eascapeco.scinemapr.bo.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eascapeco.scinemapr.api.exception.CAuthenticationEntryPointException;
import com.eascapeco.scinemapr.api.model.Result;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public Result authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return new Result("400", "ttt");
    }
}
