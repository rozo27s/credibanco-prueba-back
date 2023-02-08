package com.credibanco.assessment.framework.handler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credibanco.assessment.card.api.client.exceptions.BadRequestException;
import com.credibanco.assessment.card.api.client.exceptions.InternalServerException;
import com.credibanco.assessment.card.api.client.exceptions.InvalidRequestException;
import com.credibanco.assessment.framework.util.ResponseEntityUtil;
import com.credibanco.assessment.framework.validation.IRestResponse;
import com.credibanco.assessment.framework.validation.ValidationError;
import com.credibanco.assessment.transaction.api.client.exceptions.BadRequestExceptionTransaction;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author ajrozo
 *
 */
@Log4j2
@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<IRestResponse<List<ValidationError>>> invalidRegistrant(final InvalidRequestException ex) {
        final List<ValidationError> errors = ex.getErrors();
        return ResponseEntityUtil.createResponseValidationError(errors);
    }

    @ExceptionHandler({ Exception.class, NullPointerException.class })
    public ResponseEntity<Object> errorsGlobal(final Exception ex) {
        log.error(ex);
        return ResponseEntityUtil.createResponseError(ex);
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> badRequest(final Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntityUtil.createBadResponse(ex);
    }

    @ExceptionHandler({ BadRequestExceptionTransaction.class })
    public ResponseEntity<Object> badRequestTransaction(final Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntityUtil.createBadResponseTransaction(ex);
    }

    @ExceptionHandler({ InternalServerException.class })
    public ResponseEntity<Object> internalServer(final Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntityUtil.createInternalException(ex);
    }
}
