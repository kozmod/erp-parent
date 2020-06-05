package ru.aora.erp.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static ru.aora.erp.presentation.controller.TestController.MAPPING;

/**
 * test controller
 */
@SuppressWarnings("ALL")
@Deprecated
@Controller
@RequestMapping(MAPPING)
public class TestController {

    public static final String MAPPING = "/test";

    @GetMapping
    public @ResponseBody
    String get() {
        return "TEST get";
    }
}
