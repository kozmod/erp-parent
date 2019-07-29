package ru.aora.erp.model.controller;

public final class HttpUtils {
    private static final String REDIRECT_PREFIX = "redirect:";

    public static String redirectTo(String mapping){
        return REDIRECT_PREFIX.concat(mapping);
    }

}
