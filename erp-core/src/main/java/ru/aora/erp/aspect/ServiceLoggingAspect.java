package ru.aora.erp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aora.erp.utils.common.CommonUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public final class ServiceLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Pointcut("within(ru.aora.erp.domain.service.*)")
    public void callAllServiceInPackage() {
    }

    @Before("callAllServiceInPackage()")
    public void logBeforeCallPackageMethod(JoinPoint jp) {
        final String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.debug("Before call service method: " + jp.toString() + ", args=[" + args + "]");
    }

    @AfterReturning(pointcut = "callAllServiceInPackage()", returning = "returnedValue")
    public void logAfterCallPackageMethod(JoinPoint jp, Object returnedValue) {
        LOGGER.debug("After returning by service method: " + jp.toString() + ", returned value=" + returnedValue.toString());
    }

    @AfterThrowing(pointcut = "callAllServiceInPackage()", throwing = "ex")
    public void logAfterThrownPackageMethod(JoinPoint jp, Exception ex) {
        LOGGER.error("After throwing exception by service method: " + jp.toString() + ",\n exception:\n" + CommonUtils.getStackTrace(ex));
    }
}
