package com.twinzom.apexa.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twinzom.apexa.dao.jpo.AcctMastPo;

@Repository
public interface AcctMastRepository extends CrudRepository<AcctMastPo, Long> {

	@Query( "SELECT a FROM AcctMastPo a WHERE 1=1 "
			+ " AND a.acctExtCdePri in :acctExtCdePris "
			+ " AND a.acctStat = 'A' " )
	public List<AcctMastPo> findByAcctExtCdePri (@Param("acctExtCdePris") List<String> acctExtCdePris);
	
	@Query( "SELECT a FROM AcctMastPo a WHERE 1=1 "
			+ " AND a.acctExtCdePri = :acctExtCdePri "
			+ " AND a.acctStat = 'A' " )
	public AcctMastPo findByAcctExtCdePri (@Param("acctExtCdePri") String acctExtCdePri);
	
}
