package com.twinzom.apexa.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twinzom.apexa.dao.jpo.SecMastPo;

@Repository
public interface SecMastRepository extends CrudRepository<SecMastPo, Long> {

}
