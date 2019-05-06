package com.twinzom.apexa.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twinzom.apexa.dao.jpo.AcctMastPo;
import com.twinzom.apexa.dao.jpo.TxnMastPo;

@Repository
public interface TxnMastRepository extends CrudRepository<TxnMastPo, Long> {

	@Query( "SELECT t FROM TxnMastPo t WHERE 1=1 "
			+ " AND t.acid in :acids "
			+ " AND t.cnlInd = 'N' " )
	public List<TxnMastPo> findByAcid (@Param("acids")List<Long> acids);
	
}
