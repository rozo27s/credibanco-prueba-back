package com.credibanco.assessment.framework.translators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.card.model.Status;
import com.credibanco.assessment.framework.pattern.Translator;

/* ajrozo */
@Component
@Qualifier("statusDtoTranslate")
public class StatusDtoTranslate implements Translator<StatusDto, Status> {

    @Override
    public Status to(StatusDto input) {
        return Objects.isNull(input) ? null : Status.builder()
                .statusId(input.getEstadoId())
                .statusName(input.getEstado())
                .build();
    }
}
