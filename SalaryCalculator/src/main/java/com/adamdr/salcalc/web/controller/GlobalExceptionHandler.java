package com.adamdr.salcalc.web.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
@Log4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public String resourceAccessExceptionHandler(ResourceAccessException ex, Model model) {
        log.error("Resource access exception: " + ex);
        model.addAttribute("exception", "Connection to Currency API is currently unavailable. Check your internet connection or try again later.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex, Model model) {
        log.error("Unknown exception: " + ex);
        model.addAttribute("exception", "404 Error");
        return "error";
    }
}
