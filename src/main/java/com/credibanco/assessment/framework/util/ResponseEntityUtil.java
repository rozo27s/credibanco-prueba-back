package com.credibanco.assessment.framework.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.credibanco.assessment.card.dto.enums.ResponseStatusCodes;
import com.credibanco.assessment.framework.constants.ConfigurationConstants;
import com.credibanco.assessment.framework.validation.IRestResponse;
import com.credibanco.assessment.framework.validation.ResponseStatus;
import com.credibanco.assessment.framework.validation.ResponseStatusCode;
import com.credibanco.assessment.framework.validation.RestResponse;
import com.credibanco.assessment.framework.validation.ValidationError;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ajrozo
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntityUtil {

    public static <T> ResponseEntity<IRestResponse<T>> createResponseEntity(final IRestResponse<T> response) {
        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatusCode()))
                .body(response);
    }

    public static ResponseEntity<IRestResponse<List<ValidationError>>> createResponseValidationError(
            final List<ValidationError> errors) {
        
        final RestResponse<List<ValidationError>> fullResponse = new RestResponse<>();
        if (errors != null && !errors.isEmpty()) {
            final ResponseStatus status = getErrorResponseStatus("Validation error");
            fullResponse.setResponse(errors);
            fullResponse.setResponseStatus(status);
            fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        } else {
            fullResponse.setResponseStatus(ResponseStatus.builder()
                    .codigo(ResponseStatusCode.ERROR.toString())
                    .build());
            fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        return createResponseEntity(fullResponse);
    }

    public static <T> ResponseEntity<IRestResponse<T>> createSuccessfulResponse(
            final String message,
            final int httpStatusCode,
            final T response) {
        final ResponseStatus status = getSuccessResponseStatus(message);
        final RestResponse<T> fullResponse = new RestResponse<>();
        fullResponse.setResponseStatus(status);
        fullResponse.setHttpStatusCode(httpStatusCode);
        fullResponse.setResponse(response);
        return createResponseEntity(fullResponse);
    }

    public static ResponseEntity<Object> createResponseError(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ConfigurationConstants.RESPONSE_STATUS,
                ResponseStatus.builder()
                .codigo(ResponseStatusCode.ERROR.toString())
                .mensaje(ex.getMessage())
                .build());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    public static ResponseEntity<Object> createBadResponse(Exception ex) {
        ResponseStatusCodes responseStatus = ResponseStatusCodes.getCodeStatus(ex.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put(ConfigurationConstants.RESPONSE_STATUS,
                ResponseStatus.builder()
                .codigo(responseStatus == null ? ResponseStatusCode.ERROR.toString() : responseStatus.getResponseCode())
                .mensaje(ex.getMessage())
                .build());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    private static ResponseStatus getErrorResponseStatus(final String message) {
        return ResponseStatus.builder()
                .mensaje(message)
                .codigo(ResponseStatusCode.ERROR.toString())
                .build();
    }

    private static ResponseStatus getSuccessResponseStatus(final String message) {
        return ResponseStatus.builder()
                .mensaje(message)
                .codigo(ResponseStatusCode.OK.toString())
                .build();
    }

    public static ResponseEntity<Object> createInternalException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        ResponseStatusCodes responseStatus = ResponseStatusCodes.getCodeStatus(ex.getMessage());
        response.put(ConfigurationConstants.RESPONSE_STATUS,
                ResponseStatus.builder()
                .codigo(responseStatus == null ? ResponseStatusCode.ERROR.toString() : responseStatus.getResponseCode())
                .mensaje(ex.getMessage())
                .build());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    public static ResponseEntity<Object> createBadResponseTransaction(Exception ex) {
        ResponseStatusCodes responseStatus = ResponseStatusCodes.getCodeStatus(ex.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put(ConfigurationConstants.RESPONSE_STATUS,
                ResponseStatus.builder()
                .codigo(responseStatus == null ? ResponseStatusCode.ERROR.toString() : responseStatus.getResponseCode())
                .mensaje(ex.getMessage())
                .estadoTransaccion(ResponseStatusCodes.REJECT.getResponseDetail())
                .build());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
