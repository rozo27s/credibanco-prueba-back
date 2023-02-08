package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto implements Serializable {

    private Long cardId;
    @Size(min = 16, max = 19, message = "El tamaño no es correcto, min = 16 - max = 19")
    private String pan;
    @NotBlank
    private String titular;
    @Size(min = 10, max = 15, message = "El tamaño no es correcto, min = 10 - max = 15")
    private String cedula;
    private String telefono;
    private Integer codigoValidacion;
    private TypeCardDto tipo;
    private StatusDto estado;

    private static final long serialVersionUID = 8094153480314594701L;

}
