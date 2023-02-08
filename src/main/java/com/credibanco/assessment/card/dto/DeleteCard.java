package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
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
public class DeleteCard implements Serializable {

    @Size(min = 16, max = 19, message = "El tamaño no es correcto, min = 16 - max = 19")
    @NotBlank
    private String pan;
    private Integer codigoValidacion;

    private static final long serialVersionUID = 8034153480314594701L;

}
