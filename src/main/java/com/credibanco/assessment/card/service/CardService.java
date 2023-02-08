package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.dto.CardDto;

/** @author ajrozo */
public interface CardService {

    CardDto getCardById(Long id);

    CardDto createCard(CardDto cardDto);

    CardDto getCardByPan(String pan);

    void deleteCard(CardDto cardSaveDto);

    List<CardDto> getCards();

}
