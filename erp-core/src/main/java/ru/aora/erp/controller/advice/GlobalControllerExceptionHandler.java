package ru.aora.erp.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.aora.erp.controller.exception.DtoValidationException;
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
    private final static String VALIDATION_ERRORS_MAP = "validation_errors";

    private final static String SERVICE_ERROR_TEMPLATE = "error";
    private final static String VALIDATION_ERROR_TEMPLATE = "validationerror";

//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView runtimeException(HttpServletRequest req, Exception ex) {
//        final ModelAndView mav = new ModelAndView();
//        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        mav.addObject(TIMESTAMP, LocalDateTime.now());
//        mav.addObject(PATH, req.getRequestURI());
//        mav.addObject(ERROR, ex.getClass());
//        mav.addObject(MESSAGE, ex.getMessage());
//        mav.addObject(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
//        mav.addObject(TRACE, CommonUtils.getStackTrace(ex));
//        mav.setViewName(SERVICE_ERROR_TEMPLATE);
//        return mav;
//    }

    @ExceptionHandler(DtoValidationException.class)
    public ModelAndView validationError(DtoValidationException ex) {
        final ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject(TIMESTAMP, LocalDateTime.now());
        mav.addObject(VALIDATION_ERRORS_MAP, ex.getBindingResult().getAllErrors());
        mav.setViewName(VALIDATION_ERROR_TEMPLATE);
        return mav;
    }
}