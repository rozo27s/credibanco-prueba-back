package com.credibanco.assessment.transaction.service;

import java.time.LocalDateTime;
import java.util.List;

import com.credibanco.assessment.transaction.dto.TransactionDto;

/** @author ajrozo */
public interface TransactionService {

    List<TransactionDto> getTransactionByCreationDate(LocalDateTime startTime, LocalDateTime endTime);

    TransactionDto saveTransaction(TransactionDto transactionDto);

    TransactionDto cancelTransaction(TransactionDto transaction);

    TransactionDto getTransactionByReference(String reference);

    List<TransactionDto> getTransactions();

}
