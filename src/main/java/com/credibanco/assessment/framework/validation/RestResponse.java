package com.credibanco.assessment.framework.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

/**
 * 
 * @author ajrozo
 *
 * @param <T>
 */
@Setter
public class RestResponse<T> implements IRestResponse<T> {

    private ResponseStatus responseStatus;
    private T response;
    @JsonIgnore
    private int httpStatusCode;

    @Override
    public ResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    @Override
    public T getResponse() {
        return this.response;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
