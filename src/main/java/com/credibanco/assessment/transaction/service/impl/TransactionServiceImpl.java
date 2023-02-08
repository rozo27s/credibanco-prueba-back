package com.credibanco.assessment.transaction.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.dto.enums.ResponseStatusCodes;
import com.credibanco.assessment.card.model.Status;
import com.credibanco.assessment.framework.jpa.crudrepository.StatusRepository;
import com.credibanco.assessment.framework.jpa.crudrepository.TransactionRepository;
import com.credibanco.assessment.framework.pattern.Translator;
import com.credibanco.assessment.transaction.dto.TransactionDto;
import com.credibanco.assessment.transaction.model.Transaction;
import com.credibanco.assessment.transaction.service.TransactionService;

/** @author ajrozo */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    @Qualifier("transactionTranslate")
    private Translator<Transaction, TransactionDto> transactionTranslate;

    @Autowired
    @Qualifier("transactionDtoTranslate")
    private Translator<TransactionDto, Transaction> transactionDtoTranslate;

    @Override
    public List<TransactionDto> getTransactionByCreationDate(LocalDateTime startTime, LocalDateTime endTime) {
        List<TransactionDto> listTransactionDtos = new ArrayList<>();
        transactionRepository.findByCreationDateBetween(startTime, endTime)
                .forEach(t -> listTransactionDtos.add(transactionTranslate.to(t)));
        return listTransactionDtos;
    }

    @Override
    public TransactionDto getTransactionByReference(String reference) {
        return transactionTranslate.to(transactionRepository.findByReference(reference));
    }

    @Override
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Status status = statusRepository.findByStatusName(ResponseStatusCodes.SUCCESS.getResponseDetail());
        transactionDto.setEstado(StatusDto.builder().estadoId(status.getStatusId()).build());
        return transactionTranslate.to(transactionRepository.save(transactionDtoTranslate.to(transactionDto)));
    }

    @Override
    public TransactionDto cancelTransaction(TransactionDto transactionDto) {
        Status status = statusRepository.findByStatusName(ResponseStatusCodes.CANCEL.getResponseDetail());
        transactionDto.setEstado(StatusDto.builder().estadoId(status.getStatusId()).build());
        return transactionTranslate.to(transactionRepository.save(transactionDtoTranslate.to(transactionDto)));
    }

}
