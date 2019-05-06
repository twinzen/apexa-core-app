package com.twinzom.apexa.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twinzom.apexa.dao.jpo.TxnEditHistPo;

@Repository
public interface TxnEditHistRepository extends CrudRepository<TxnEditHistPo, Long> {

}
