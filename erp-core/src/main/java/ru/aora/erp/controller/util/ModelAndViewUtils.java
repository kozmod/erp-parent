package ru.aora.erp.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

public final class ModelAndViewUtils {

    private final static String TIMESTAMP = "timestamp";
    private final static String VALIDATION_ERRORS_MAP = "validation_errors";
    private final static String ERROR_TEMPLATE = "validationerror";

    private ModelAndViewUtils() {
    }

    public static ModelAndView error(BindingResult bindingResult) {
        final ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject(TIMESTAMP, LocalDateTime.now());
        mav.addObject(VALIDATION_ERRORS_MAP, bindingResult.getAllErrors());
        mav.setViewName(ERROR_TEMPLATE);
        return mav;
    }
}
