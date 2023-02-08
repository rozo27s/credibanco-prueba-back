package com.credibanco.assessment.framework.jpa.crudrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Card;

/**
 * 
 * @author ajrozo
 *
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByPan(String pan);

}
