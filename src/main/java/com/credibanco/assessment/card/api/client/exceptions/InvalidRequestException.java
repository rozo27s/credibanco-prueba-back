package com.credibanco.assessment.card.api.client.exceptions;

import java.util.List;

import com.credibanco.assessment.framework.validation.ValidationError;

/** @author ajrozo */
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 5986087872673515973L;
    private final List<ValidationError> errors;

    public InvalidRequestException(final List<ValidationError> errors) {
        super("InvalidRequestException");
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return this.errors;
    }
}
