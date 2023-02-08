package com.credibanco.assessment.transaction.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.api.client.exceptions.InternalServerException;
import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.Response;
import com.credibanco.assessment.card.dto.enums.ResponseStatusCodes;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.framework.constants.Messages;
import com.credibanco.assessment.transaction.api.client.exceptions.BadRequestExceptionTransaction;
import com.credibanco.assessment.transaction.dto.TransactionCancelDto;
import com.credibanco.assessment.transaction.dto.TransactionDto;
import com.credibanco.assessment.transaction.service.TransactionService;

/** @author ajrozo */
@Component
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardController cardController;

    public Object getTransactions() {
        List<TransactionDto> list = transactionService.getTransactions();
        List<TransactionDto> listProcesseDtos = new ArrayList<>();
        list.forEach(t -> {
            CardDto cardDto = t.getPan();
            cardDto.setPan(cardController.enmascararPan(t.getPan().getPan()));
            t.setPan(cardDto);
            listProcesseDtos.add(t);
        });
        return listProcesseDtos;
    }

    public Object getTransactionByCreationDate(LocalDate startTime, LocalDate endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startTime, LocalTime.of(0, 0, 0));
        LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.of(23, 59, 59, 999999999));
        List<TransactionDto> list = transactionService.getTransactionByCreationDate(startDateTime, endDateTime);
        List<TransactionDto> listProcesseDtos = new ArrayList<>();
        list.forEach(t -> {
            CardDto cardDto = t.getPan();
            cardDto.setPan(cardController.enmascararPan(t.getPan().getPan()));
            t.setPan(cardDto);
            listProcesseDtos.add(t);
        });
        return listProcesseDtos;
    }

    public Object createTransaction(TransactionDto transactionDto) {
        CardDto cardDto = cardService.getCardByPan(transactionDto.getPan().getPan());
        if (Objects.isNull(cardDto)) {
            throw new BadRequestExceptionTransaction(ResponseStatusCodes.NO_EXIST.getResponseDetail());
        } else if (!cardDto.getEstado().getEstado().equalsIgnoreCase(ResponseStatusCodes.ENROLL.getResponseDetail())) {
            throw new BadRequestExceptionTransaction(ResponseStatusCodes.NO_ENROLL.getResponseDetail());
        }
        try {
            transactionDto.setFechaCreacion(LocalDateTime.now());
            transactionDto.setPan(cardDto);
            transactionService.saveTransaction(transactionDto);
            HashMap<String, String> status = new HashMap<>();
            status.put(Messages.CODE, "00");
            status.put(Messages.MESSAGE, "Compra exitosa");
            status.put(Messages.STATUS_TRANSACTION, "Aprobada");
            status.put(Messages.REFERENCE, transactionDto.getReferencia());
            return Response.builder().responseStatus(status).build();
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.FALLIDO.getResponseDetail());
        }

    }

    public Object cancelTransaction(TransactionCancelDto transaction) {
        TransactionDto transactionDto = transactionService.getTransactionByReference(transaction.getReferencia());
        if (Objects.isNull(transactionDto)) {
            throw new BadRequestExceptionTransaction(ResponseStatusCodes.REFERENCE_NO_VALID.getResponseDetail());
        } else {
            Long diferenciaLong = System.currentTimeMillis()
                    - transactionDto.getFechaCreacion().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if ((diferenciaLong / 1000) > 300) {
                throw new BadRequestExceptionTransaction(ResponseStatusCodes.NOT_ANULATION.getResponseDetail());
            }
        }
        try {
            transactionService.cancelTransaction(transactionDto);
            HashMap<String, String> status = new HashMap<>();
            status.put(Messages.CODE, "00");
            status.put(Messages.MESSAGE, ResponseStatusCodes.CANCEL.getResponseDetail());
            status.put(Messages.REFERENCE, transactionDto.getReferencia());
            return Response.builder().responseStatus(status).build();
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.NOT_ANULATION.getResponseDetail());
        }
    }

}
