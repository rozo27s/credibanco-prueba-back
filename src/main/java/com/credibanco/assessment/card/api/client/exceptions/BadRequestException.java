package com.credibanco.assessment.card.api.client.exceptions;

/** @author ajrozo */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -5372939932064008617L;

    public BadRequestException(String detail) {
        super(detail);
    }

}
