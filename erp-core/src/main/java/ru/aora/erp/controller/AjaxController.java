package ru.aora.erp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {

    @RequestMapping("/controller")
    public String t() {
        return "<div>aaa<div>";
    }
}
