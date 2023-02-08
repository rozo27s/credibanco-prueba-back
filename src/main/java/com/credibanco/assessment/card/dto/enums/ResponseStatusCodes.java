package com.credibanco.assessment.card.dto.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author ajrozo
 *
 */
@Getter
@AllArgsConstructor
public enum ResponseStatusCodes {
    
    OK("00", "Exito"),
    APPROVED("00", "Aprobada"),
    REJECT("01", "Rechazada"),
    FALLIDO("01", "Fallido"),
    NO_EXIST("01", "Tarjeta no existe"),
    INVALID("02", "Numero de validacion invalido"),
    SUCCESS("00", "Compra exitosa"),
    CARD_NO_EXIXT("01", "Tarjeta no existe"),
    CARD_DELETE("00", "Se ha eliminado la tarjeta"),
    CARD_NO_DELETE("01", "No se ha eliminado la tarjeta"),
    ENROLL("01", "Enrolada"),
    NO_ENROLL("02", "Tarjeta no enrolada"),
    CANCEL("00", "Compra anulada"),
    REFERENCE_NO_VALID("01", "Numero de referencia invalido"),
    NOT_ANULATION("02", "No se puede anular transacción");

    private String responseCode;
    private String responseDetail;

    public static ResponseStatusCodes getCodeStatus(String code) {
        for (ResponseStatusCodes enumResponse : ResponseStatusCodes.values()) {
            if (Objects.equals(enumResponse.getResponseDetail(), code)) {
                return enumResponse;
            }
        }
        return null;
    }

}
