package com.credibanco.assessment.card.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.api.client.exceptions.BadRequestException;
import com.credibanco.assessment.card.api.client.exceptions.InternalServerException;
import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.DeleteCard;
import com.credibanco.assessment.card.dto.EnrollCard;
import com.credibanco.assessment.card.dto.Response;
import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.dto.enums.ResponseStatusCodes;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.framework.constants.Messages;
import com.credibanco.assessment.framework.util.RandGen;

/** @author ajrozo */
@Component
public class CardController {

    @Autowired
    private CardService cardService;

    public CardDto getCardByPan(String pan) {
        if (!isNumeric(pan)) {
            throw new BadRequestException(Messages.NOT_NUMERIC_PAN);
        }
        CardDto cardDto = cardService.getCardByPan(pan);
        if (Objects.nonNull(cardDto)) {
            cardDto.setPan(this.enmascararPan(cardDto.getPan()));
            cardDto.setEstado(StatusDto.builder().estado(cardDto.getEstado().getEstado()).build());
        }
        return cardDto;
    }

    public List<CardDto> getCards(String pan) {
        if (Objects.isNull(pan)) {
            return getAllCards();
        } else {
            CardDto cardDto = getCardByPan(pan);
            if (Objects.nonNull(cardDto)) {
                return Arrays.asList(getCardByPan(pan));
            } else {
                return Collections.emptyList();
            }
        }
    }

    private List<CardDto> getAllCards() {
        List<CardDto> list = cardService.getCards();
        List<CardDto> cardDtos = new ArrayList<>();
        list.forEach(c -> {
            c.setPan(enmascararPan(c.getPan()));
            cardDtos.add(c);
        });
        return cardDtos;
    }

    public Object createCard(CardDto cardDto) {
        if (!isNumeric(cardDto.getPan())) {
            throw new BadRequestException(Messages.NOT_NUMERIC_PAN);
        }
        try {
            cardDto.setCodigoValidacion(RandGen.randNum());
            CardDto cardSaveDto = cardService.createCard(cardDto);
            HashMap<String, String> status = new HashMap<>();
            status.put(Messages.CODE, "00");
            status.put(Messages.MESSAGE, "Exito");
            status.put(Messages.CODE_VERIFICATION, cardSaveDto.getCodigoValidacion().toString());
            status.put(Messages.PAN, this.enmascararPan(cardSaveDto.getPan()));

            return Response.builder().responseStatus(status).build();
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.FALLIDO.getResponseDetail());
        }
    }

    public Object deleteCard(@Valid DeleteCard enrollCard) {
        if (Objects.nonNull(enrollCard.getPan()) && Objects.nonNull(enrollCard.getCodigoValidacion())) {
            deleteCarByPanAndCode(enrollCard);
        } else if (Objects.nonNull(enrollCard.getPan())) {
            deleteCarByPan(enrollCard);
        }
        HashMap<String, String> status = new HashMap<>();
        status.put(Messages.CODE, "00");
        status.put(Messages.MESSAGE, ResponseStatusCodes.CARD_DELETE.getResponseDetail());
        return Response.builder().responseStatus(status).build();
    }

    public void deleteCarByPanAndCode(DeleteCard enrollCard) {
        if (!isNumeric(enrollCard.getPan())) {
            throw new BadRequestException(Messages.NOT_NUMERIC_PAN);
        }
        CardDto cardDelete = cardService.getCardByPan(enrollCard.getPan());
        if (Objects.isNull(cardDelete) || !cardDelete.getCodigoValidacion().equals(enrollCard.getCodigoValidacion())) {
            throw new BadRequestException(ResponseStatusCodes.CARD_NO_DELETE.getResponseDetail());
        }
        try {
            cardService.deleteCard(cardDelete);
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.FALLIDO.getResponseDetail());
        }
    }

    public void deleteCarByPan(DeleteCard enrollCard) {
        if (!isNumeric(enrollCard.getPan())) {
            throw new BadRequestException(Messages.NOT_NUMERIC_PAN);
        }
        CardDto cardDelete = cardService.getCardByPan(enrollCard.getPan());
        if (Objects.isNull(cardDelete)) {
            throw new BadRequestException(ResponseStatusCodes.CARD_NO_DELETE.getResponseDetail());
        }
        try {
            cardService.deleteCard(cardDelete);
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.FALLIDO.getResponseDetail());
        }
    }

    public Object enrollCar(EnrollCard enrollCard) {
        if (!isNumeric(enrollCard.getPan())) {
            throw new BadRequestException(Messages.NOT_NUMERIC_PAN);
        }
        CardDto cardSaveDto = cardService.getCardByPan(enrollCard.getPan());
        if (Objects.isNull(cardSaveDto)) {
            throw new BadRequestException("Tarjeta no existe");
        } else if (!cardSaveDto.getCodigoValidacion().equals(enrollCard.getCodigoValidacion())) {
            throw new BadRequestException("Numero de validacion invalido");
        }
        try {
            cardSaveDto.setEstado(StatusDto.builder().estadoId(2L).build());
            CardDto cardUpdate = cardService.createCard(cardSaveDto);
            HashMap<String, String> status = new HashMap<>();
            status.put(Messages.CODE, "00");
            status.put(Messages.MESSAGE, "Exito");
            status.put(Messages.CODE_VERIFICATION, cardSaveDto.getCodigoValidacion().toString());
            status.put(Messages.PAN, this.enmascararPan(cardUpdate.getPan()));

            return Response.builder().responseStatus(status).build();
        } catch (Exception e) {
            throw new InternalServerException(ResponseStatusCodes.FALLIDO.getResponseDetail());
        }
    }

    public String enmascararPan(String pan) {
        String panTemp = pan.substring(0, pan.length() - 4);
        StringBuilder caracteres = new StringBuilder();
        int con = 0;
        while (panTemp.length() > con) {
            caracteres.append("*");
            con++;
        }
        return caracteres + pan.substring(pan.length() - 4, pan.length());
    }

    private boolean isNumeric(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
