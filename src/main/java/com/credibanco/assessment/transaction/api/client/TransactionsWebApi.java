package com.credibanco.assessment.transaction.api.client;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.framework.constants.ConfigurationConstants;
import com.credibanco.assessment.framework.util.ValidUtil;
import com.credibanco.assessment.transaction.controller.TransactionController;
import com.credibanco.assessment.transaction.dto.TransactionCancelDto;
import com.credibanco.assessment.transaction.dto.TransactionDto;

/** @author ajrozo */
@RestController
@CrossOrigin("*")
@RequestMapping(
        value = ConfigurationConstants.REQUEST_TRANSACTIONS,
        produces = { MediaType.APPLICATION_JSON_VALUE })
public class TransactionsWebApi {
    
    @Autowired
    private TransactionController transactionController;
    
    @GetMapping(ConfigurationConstants.REQUEST_FIND_TRANSACTION)
    public ResponseEntity<Object> getTransaction(
            @DateTimeFormat(pattern = ConfigurationConstants.SIMPLE_DATE_FORMAT)
            @RequestParam(required = true) final LocalDate initialDate,
            @DateTimeFormat(pattern = ConfigurationConstants.SIMPLE_DATE_FORMAT)
            @RequestParam(required = true) final LocalDate endDate) {
        return ResponseEntity.ok(transactionController.getTransactionByCreationDate(initialDate, endDate));
    }
    
    @PostMapping(ConfigurationConstants.REQUEST_CREATE_TRANSACTION)
    public ResponseEntity<Object> createTransaction(
            @Valid final @RequestBody TransactionDto transactionDto,
            final BindingResult result) {
        ValidUtil.validateBindingResult(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionController.createTransaction(transactionDto));
    }
    
    @PutMapping(ConfigurationConstants.REQUEST_CANCEL_TRANSACTION)
    public ResponseEntity<Object> cancelTransaction(@Valid final @RequestBody TransactionCancelDto transaction) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionController.cancelTransaction(transaction));
    }
    
}
