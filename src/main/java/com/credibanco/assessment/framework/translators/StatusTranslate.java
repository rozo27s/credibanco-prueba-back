package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.model.Status;
import com.credibanco.assessment.framework.pattern.Translator;

/* ajrozo */
@Component
@Qualifier("statusTranslate")
public class StatusTranslate implements Translator<Status, StatusDto> {

    @Override
    public StatusDto to(Status input) {
        return Objects.isNull(input) ? null : StatusDto.builder()
                .estadoId(input.getStatusId())
                .estado(input.getStatusName())
                .build();
    }
}
