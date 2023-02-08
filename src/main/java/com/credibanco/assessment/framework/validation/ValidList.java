package com.credibanco.assessment.framework.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.experimental.Delegate;

/**
 * 
 * @author ajrozo
 * @param <E> list generic valid
 */
@Data
public class ValidList<E> implements List<E> {

    @Valid
    @Delegate
    private List<E> list = new ArrayList<>();

}
