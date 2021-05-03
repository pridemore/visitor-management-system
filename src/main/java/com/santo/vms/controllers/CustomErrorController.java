package com.santo.vms.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object errorExceptionType = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);


        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("statusCode", statusCode);
                //return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                //return "error-500";
                model.addAttribute("statusCode", statusCode);
            }
        }
        model.addAttribute("statusCode", status);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
