package com.credibanco.assessment.framework.jpa.crudrepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.transaction.model.Transaction;

/**
 * 
 * @author ajrozo
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    List<Transaction> findByCreationDateBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Transaction findByReference(String reference);

}
