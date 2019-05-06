package com.twinzom.apexa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twinzom.apexa.dao.dto.AccountDto;
import com.twinzom.apexa.dao.jpo.AcctMastPo;
import com.twinzom.apexa.dao.repository.AcctMastRepository;

@Component
public class AccountDao {

	@Autowired 
	private AcctMastRepository acctMastRepository;
	
	public List<AccountDto> getAcctsByExtCdePris (List<String> acctExtCdePris) {

		List<AcctMastPo> pos = acctMastRepository.findByAcctExtCdePri(acctExtCdePris);
		
		List<AccountDto> dtos = new ArrayList<AccountDto>();
		
		for (AcctMastPo po : pos) {
			AccountDto dto = convertPoToDTo(po);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	public AccountDto getAcctByExtCdePri (String acctExtCdePri) {

		AcctMastPo po = acctMastRepository.findByAcctExtCdePri(acctExtCdePri);
		
		return convertPoToDTo(po);
	}
	
	private AccountDto convertPoToDTo (AcctMastPo po) {
		
		AccountDto dto = new AccountDto();
		
		dto.setAcid(po.getAcid());
		dto.setAcctExtCdePri(po.getAcctExtCdePri());
		dto.setAcctName(po.getAcctName());
		dto.setAcctMthd(po.getAcctMthd());
		dto.setAcctCcy(po.getAcctCcy());
		dto.setStrtDtTm(po.getStrtDtTm());
		dto.setTermDtTm(po.getTermDtTm());
		dto.setAcctStat(po.getAcctStat());
		dto.setCrtDtTm(po.getCrtDtTm());
		dto.setUpdtDtTm(po.getUpdtDtTm());
		
		return dto;
	}
	
}
