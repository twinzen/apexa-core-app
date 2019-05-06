package com.twinzom.apexa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.twinzom.apexa.dao.dto.SecurityDto;
import com.twinzom.apexa.dao.dto.SecurityPriceDto;
import com.twinzom.apexa.dao.dto.TransactionDto;
import com.twinzom.apexa.dao.jpo.SecMastPo;
import com.twinzom.apexa.dao.jpo.SecPrcPo;
import com.twinzom.apexa.dao.jpo.TxnMastPo;
import com.twinzom.apexa.dao.repository.SecMastRepository;
import com.twinzom.apexa.dao.repository.SecPrcRepository;
import com.twinzom.apexa.dao.repository.TxnMastRepository;

@Component
public class SecurityDao {

	@Autowired 
	private SecMastRepository secMastRepository;
	
	@Autowired 
	private SecPrcRepository secPrcRepository;
	
	public SecurityDto getSecsBySecid (Long secid) {
		
		if (secid == null) 
			return null;
		
		SecMastPo po = secMastRepository.findById(secid).get();
		
		SecurityDto dto = convertPoToDTo(po);
		
		return dto;
		
	}
	
	
	public List<SecurityDto> getSecsBySecids (List<Long> secids) {

		if (secids == null || secids.size() <= 0) 
			return null;
		
		List<SecMastPo> pos = Lists.newArrayList(secMastRepository.findAllById(secids));
		
		List<SecurityDto> dtos = new ArrayList<SecurityDto>();
		
		for (SecMastPo po : pos) {
			SecurityDto dto = convertPoToDTo(po);
			dtos.add(dto);
		}
		
		return dtos;
		
	}
	
	public SecurityDto getSecsWithPriceBySecid (Long secid) {
		
		if (secid == null) 
			return null;

		SecurityDto dto = getSecsBySecid(secid);
		
		SecPrcPo spPo = secPrcRepository.findLatestBySecId(secid);
		
		dto.setSecPrc(convertSpPoToSpDTo(spPo));
		
		return dto;
		
	}
	
	
	public List<SecurityDto> getSecsWithPriceBySecids (List<Long> secids) {

		if (secids == null || secids.size() <= 0) 
			return null;

		List<SecurityDto> dtos = getSecsBySecids(secids);
		
		List<SecPrcPo> spPos = secPrcRepository.findLatestBySecIds(secids);

		for (SecurityDto dto : dtos) {
			for (SecPrcPo spPo : spPos) {
				System.out.print("Look up price: "+dto.getSecid()+" vs "+spPo.getSecid());
				if (dto.getSecid().equals(spPo.getSecid())) {
					System.out.println("FOUND");
					dto.setSecPrc(convertSpPoToSpDTo(spPo));
					break;
				} else {
					System.out.println("NOT");
				}
			}
		}
		
		return dtos;
		
	}
	
	private SecurityDto convertPoToDTo (SecMastPo po) {
		
		SecurityDto dto = new SecurityDto();
		
		dto.setSecid(po.getSecid());
		dto.setSecCdePri(po.getSecCdePri());
		dto.setSecTypeCde(po.getSecTypeCde());
		dto.setName(po.getName());
		dto.setLoclCcy(po.getLoclCcy());
		dto.setSecStat(po.getSecStat());
		dto.setPrcDiv(po.getPrcDiv());
		dto.setSrcSysCde(po.getSrcSysCde());
		dto.setMturDt(po.getMturDt());
		dto.setCrtDtTm(po.getCrtDtTm());
		dto.setUpdDtTm(po.getUpdtDtTm());
		
		return dto;
	}
	
	private SecurityPriceDto convertSpPoToSpDTo (SecPrcPo po) {
		
		SecurityPriceDto dto = new SecurityPriceDto();
		
		dto.setMktPrc(po.getMktPrc());
		dto.setEffDtTm(po.getEffDtTm());
		
		return dto;
	}
	
}
