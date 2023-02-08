package com.credibanco.assessment.transaction.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.credibanco.assessment.card.dto.CardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCancelDto implements Serializable {

    private CardDto pan;
    @NotNull
    private String referencia;
    @NotNull
    private double totalCompra;

    private static final long serialVersionUID = 8094157880318994704L;

}
