package com.credibanco.assessment.transaction.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.StatusDto;
import com.credibanco.assessment.framework.constants.ConfigurationConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto implements Serializable {

    private Long transactionId;
    @NotNull
    private CardDto pan;
    @NotNull
    private String referencia;
    @NotNull
    private double totalCompra;
    @NotNull
    private String direccion;
    private StatusDto estado;
    @JsonFormat(pattern = ConfigurationConstants.SIMPLE_DATE_TIME_FORMAT)
    private LocalDateTime fechaCreacion;

    private static final long serialVersionUID = 8094157880314794704L;

}
