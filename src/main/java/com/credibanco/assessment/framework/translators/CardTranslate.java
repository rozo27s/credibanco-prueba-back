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
@Qualifier("cardTranslate")
public class CardTranslate implements Translator<Card, CardDto> {

    @Autowired
    @Qualifier("statusTranslate")
    private Translator<Status, StatusDto> statusTranslate;

    @Autowired
    @Qualifier("typeCardTranslate")
    private Translator<TypeCard, TypeCardDto> typeCardTranslate;

    @Override
    public CardDto to(Card input) {
        return Objects.isNull(input) ? null : CardDto.builder()
                .cardId(input.getCardId())
                .pan(input.getPan())
                .titular(input.getCardholder())
                .cedula(input.getIdentification())
                .telefono(input.getPhone())
                .codigoValidacion(input.getCodeValidation())
                .tipo(typeCardTranslate.to(input.getType()))
                .estado(statusTranslate.to(input.getStatus()))
                .build();
    }
}
