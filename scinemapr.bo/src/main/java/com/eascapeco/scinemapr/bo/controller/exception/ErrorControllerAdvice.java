package com.eascapeco.scinemapr.bo.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String detail = "";

        //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            detail = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotNull":
                    code = ErrorCode.NOT_NULL.getCode();
                    description = ErrorCode.NOT_NULL.getDescription();
                    break;
                case "Min":
                    code = ErrorCode.MIN_VALUE.getCode();
                    description = ErrorCode.MIN_VALUE.getDescription();
                    break;
            }
        }

        return new ErrorResponse(code, description, detail);
    }
}
