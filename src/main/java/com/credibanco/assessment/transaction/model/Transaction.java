package com.credibanco.assessment.transaction.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author ajrozo */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @JsonBackReference
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card pan;

    @Column(name = "reference")
    private String reference;

    @Column(name = "totalPurchase")
    private double totalPurchase;

    @Column(name = "address")
    private String address;

    @JsonBackReference
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @Basic(optional = true)
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private static final long serialVersionUID = 8094157880314794704L;

}
