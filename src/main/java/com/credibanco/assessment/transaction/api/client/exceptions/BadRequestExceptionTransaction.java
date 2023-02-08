package com.credibanco.assessment.transaction.api.client.exceptions;

/** @author ajrozo */
public class BadRequestExceptionTransaction extends RuntimeException {

    private static final long serialVersionUID = -5372939932064008617L;

    public BadRequestExceptionTransaction(String detail) {
        super(detail);
    }

}
