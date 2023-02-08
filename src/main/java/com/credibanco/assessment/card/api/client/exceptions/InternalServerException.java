package com.credibanco.assessment.card.api.client.exceptions;

/** @author ajrozo */
public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = -5372939932164008617L;

    public InternalServerException(String detail) {
        super(detail);
    }

}
