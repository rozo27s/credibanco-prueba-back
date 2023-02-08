package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Status;
import com.credibanco.assessment.framework.pattern.Translator;
import com.credibanco.assessment.transaction.dto.TransactionDto;
import com.credibanco.assessment.transaction.model.Transaction;

/* ajrozo */
@Component
@Qualifier("transactionDtoTranslate")
public class TransactionDtoTranslate implements Translator<TransactionDto, Transaction> {
    
    @Autowired
    @Qualifier("cardDtoTranslate")
    private Translator<CardDto, Card> cardDtoTranslate;
    
    @Autowired
    @Qualifier("statusDtoTranslate")
    private Translator<StatusDto, Status> statusDtoTranslate;

    @Override
    public Transaction to(TransactionDto input) {
        
        return Objects.isNull(input) ? null : Transaction.builder()
                .transactionId(input.getTransactionId())
                .pan(cardDtoTranslate.to(input.getPan()))
                .reference(input.getReferencia())
                .totalPurchase(input.getTotalCompra())
                .address(input.getDireccion())
                .status(statusDtoTranslate.to(input.getEstado()))
                .creationDate(input.getFechaCreacion())
                .build();
    }


}
