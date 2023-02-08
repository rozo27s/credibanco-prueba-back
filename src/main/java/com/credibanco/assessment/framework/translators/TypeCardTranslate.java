package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.TypeCardDto;
import com.credibanco.assessment.card.model.TypeCard;
import com.credibanco.assessment.framework.pattern.Translator;

/* ajrozo */
@Component
@Qualifier("typeCardTranslate")
public class TypeCardTranslate implements Translator<TypeCard, TypeCardDto> {

    @Override
    public TypeCardDto to(TypeCard input) {
        return Objects.isNull(input) ? null : TypeCardDto.builder()
                .tipoId(input.getTypeCardId())
                .tipo(input.getTypeCardName())
                .build();
    }
}
