package com.credibanco.assessment.framework.validation;

/**
 * 
 * @author ajrozo
 *
 * @param <T>
 */
public interface IRestResponse<T> {

    ResponseStatus getResponseStatus();

    T getResponse();

    int getHttpStatusCode();
}
