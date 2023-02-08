package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollCard implements Serializable {

    @Size(min = 16, max = 19, message = "El tamaño no es correcto, min = 16 - max = 19")
    private String pan;

    @Min(1)
    @Max(99)
    @NotNull
    private Integer codigoValidacion;

    private static final long serialVersionUID = 8034153480314594701L;

}
