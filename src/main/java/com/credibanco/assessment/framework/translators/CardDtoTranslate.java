package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.dto.TypeCardDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Status;
import com.credibanco.assessment.card.model.TypeCard;
import com.credibanco.assessment.framework.pattern.Translator;

/* ajrozo */
@Component
@Qualifier("cardDtoTranslate")
public class CardDtoTranslate implements Translator<CardDto, Card> {

    @Autowired
    @Qualifier("statusDtoTranslate")
    private Translator<StatusDto, Status> statusDtoTranslate;

    @Autowired
    @Qualifier("typeCardDtoTranslate")
    private Translator<TypeCardDto, TypeCard> typeCardDtoTranslate;

    @Override
    public Card to(CardDto input) {
        
        return Objects.isNull(input) ? null : Card.builder()
                .cardId(input.getCardId())
                .pan(input.getPan())
                .cardholder(input.getTitular())
                .identification(input.getCedula())
                .phone(input.getTelefono())
                .codeValidation(input.getCodigoValidacion())
                .type(typeCardDtoTranslate.to(input.getTipo()))
                .status(statusDtoTranslate.to(input.getEstado()))
                .build();
    }
}
