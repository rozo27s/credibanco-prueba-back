package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeCardDto implements Serializable {

    private Long tipoId;
    private String tipo;

    private static final long serialVersionUID = 8094157880314794704L;

}
