package com.mohsin.restservices.controllers;

import com.mohsin.restservices.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/hello-bean")
    public UserInfo getUserInfo() {
        return new UserInfo("Mohsin", "Raza", "Karachi");
    }

    @GetMapping("/hello-i18n")
    public String getI18NMessages(@RequestHeader(name = "Accept-Language", required = false) String language) {
        return messageSource.getMessage("hello_world", null, Locale.of(language));
    }

    @GetMapping("/hello-i18n-2")
    public String getI18NMessages() {
        return messageSource.getMessage("hello_world", null, LocaleContextHolder.getLocale());
    }
}
