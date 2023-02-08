package com.credibanco.assessment.card.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    private HashMap<String, String> responseStatus;

    private static final long serialVersionUID = 8094143480314594701L;

}
