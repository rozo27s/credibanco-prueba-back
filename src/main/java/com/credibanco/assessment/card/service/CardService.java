package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardDto;

/** @author ajrozo */
public interface CardService {

    CardDto getCardById(Long id);

    CardDto createCard(CardDto cardDto);

    CardDto getCardByPan(String pan);

    void deleteCard(CardDto cardSaveDto);

}
