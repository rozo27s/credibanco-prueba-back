package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.TypeCardDto;
import com.credibanco.assessment.card.model.TypeCard;
import com.credibanco.assessment.framework.pattern.Translator;

/* ajrozo */
@Component
@Qualifier("typeCardDtoTranslate")
public class TypeCardDtoTranslate implements Translator<TypeCardDto, TypeCard> {

    @Override
    public TypeCard to(TypeCardDto input) {
        return Objects.isNull(input) ? null : TypeCard.builder()
                .typeCardId(input.getTipoId())
                .typeCardName(input.getTipo())
                .build();
    }
}
