package ru.aora.erp.presentation.controller.exception;

import org.springframework.validation.BindingResult;

public class DtoValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public DtoValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public static void throwIfHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
    }
}
