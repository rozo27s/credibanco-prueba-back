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
@Qualifier("transactionTranslate")
public class TransactionTranslate implements Translator<Transaction, TransactionDto> {
    
    @Autowired
    @Qualifier("cardTranslate")
    private Translator<Card, CardDto> cardTranslate; 
    
    @Autowired
    @Qualifier("statusTranslate")
    private Translator<Status, StatusDto> statusTranslate;

    @Override
    public TransactionDto to(Transaction input) {
        
        return Objects.isNull(input) ? null : TransactionDto.builder()
                .transactionId(input.getTransactionId())
                .pan(cardTranslate.to(input.getPan()))
                .referencia(input.getReference())
                .totalCompra(input.getTotalPurchase())
                .direccion(input.getAddress())
                .estado(statusTranslate.to(input.getStatus()))
                .fechaCreacion(input.getCreationDate())
                .build();
    }


}
