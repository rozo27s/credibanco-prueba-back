package com.credibanco.assessment.framework.jpa.crudrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.card.model.Status;

/**
 * 
 * @author ajrozo
 *
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Status findByStatusName(String status);

}
