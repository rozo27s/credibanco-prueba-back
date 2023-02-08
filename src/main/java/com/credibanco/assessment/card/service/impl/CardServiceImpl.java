package com.credibanco.assessment.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.framework.jpa.crudrepository.CardRepository;
import com.credibanco.assessment.framework.pattern.Translator;

/** @author ajrozo */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    @Qualifier("cardTranslate")
    private Translator<Card, CardDto> cardTranslate;

    @Autowired
    @Qualifier("cardDtoTranslate")
    private Translator<CardDto, Card> cardDtoTranslate;

    @SuppressWarnings("deprecation")
    @Override
    public CardDto getCardById(Long id) {
        return cardTranslate.to(cardRepository.getById(id));
    }

    @Override
    public CardDto createCard(CardDto cardDto) {
        return cardTranslate.to(cardRepository.save(cardDtoTranslate.to(cardDto)));
    }

    @Override
    public CardDto getCardByPan(String pan) {
        return cardTranslate.to(cardRepository.findByPan(pan));
    }

    @Override
    public void deleteCard(CardDto cardDelete) {
        cardRepository.delete(cardDtoTranslate.to(cardDelete));
    }

}
