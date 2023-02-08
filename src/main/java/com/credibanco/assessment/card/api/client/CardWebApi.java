package com.credibanco.assessment.card.api.client;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.DeleteCard;
import com.credibanco.assessment.card.dto.EnrollCard;
import com.credibanco.assessment.framework.constants.ConfigurationConstants;
import com.credibanco.assessment.framework.util.ValidUtil;

/** @author ajrozo */
@RestController
@CrossOrigin("*")
@RequestMapping(
        value = ConfigurationConstants.REQUEST_CARDS,
        produces = { MediaType.APPLICATION_JSON_VALUE })
public class CardWebApi {
    
    @Autowired
    private CardController cardController;
    
    @GetMapping(ConfigurationConstants.REQUEST_FIND_CARD)
    public ResponseEntity<Object> getcard(
            @RequestParam(required = false) final String pan) {
        return ResponseEntity.ok(cardController.getCards());
    }
    
    @PostMapping(ConfigurationConstants.REQUEST_CREATE_CARD)
    public ResponseEntity<Object> createCard(
            @Valid final @RequestBody CardDto cardDto,
            final BindingResult result) {
        ValidUtil.validateBindingResult(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardController.createCard(cardDto));
    }
    
    @PutMapping(ConfigurationConstants.REQUEST_ENROLL_CARD)
    public ResponseEntity<Object> enrollCard(
            @Valid final @RequestBody EnrollCard enrollCard,
            final BindingResult result) {
        ValidUtil.validateBindingResult(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardController.enrollCar(enrollCard));
    }
    
    @DeleteMapping(ConfigurationConstants.REQUEST_DELETE_CARD)
    public ResponseEntity<Object> deleteCard(
            @Valid final @RequestBody DeleteCard enrollCard,
            final BindingResult result) {
        ValidUtil.validateBindingResult(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardController.deleteCard(enrollCard));
    }
    
}
