package ru.aora.erp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.aora.erp.utils.common.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public final class GlobalControllerExceptionHandler {

    private final static String TIMESTAMP = "timestamp";
    private final static String PATH = "path";
    private final static String ERROR = "error";
    private final static String STATUS = "status";
    private final static String MESSAGE = "message";
    private final static String TRACE = "trace";

    private final static String ERROR_TEMPLATE = "error";

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeException(HttpServletRequest req, Exception ex) {
        return toErrorModelAndView(req, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("SameParameterValue")
    private static ModelAndView toErrorModelAndView(HttpServletRequest req, Exception ex, HttpStatus status) {
        final ModelAndView mav = new ModelAndView();
        mav.setStatus(status);
        mav.addObject(TIMESTAMP, LocalDateTime.now());
        mav.addObject(PATH, req.getRequestURI());
        mav.addObject(ERROR, ex.getClass());
        mav.addObject(MESSAGE, ex.getMessage());
        mav.addObject(STATUS, status.value());
        mav.addObject(TRACE, CommonUtils.getStackTrace(ex));
        mav.setViewName(ERROR_TEMPLATE);
        return mav;
    }
}
