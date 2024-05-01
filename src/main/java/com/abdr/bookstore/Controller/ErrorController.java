package com.abdr.bookstore.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/404";
    }

    @ExceptionHandler({ Exception.class })
    public String handleAll(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            return "error/403";
        }
        return "error";
    }
}