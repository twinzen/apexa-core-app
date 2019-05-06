package com.twinzom.apexa.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twinzom.apexa.dao.jpo.SecPrcPo;

@Repository
public interface SecPrcRepository extends CrudRepository<SecPrcPo, Long> {

	@Query( "SELECT sp FROM SecPrcPo sp WHERE 1=1 "
			+ " AND sp.secid in :secids "
			+ " AND sp.expDtTm = '3999-12-31' " )
	public List<SecPrcPo> findLatestBySecIds (@Param("secids") List<Long> secids);
	
	@Query( "SELECT sp FROM SecPrcPo sp WHERE 1=1 "
			+ " AND sp.secid = :secid "
			+ " AND sp.expDtTm = '3999-12-31' " )
	public SecPrcPo findLatestBySecId (@Param("secid") Long secid);
	
}
