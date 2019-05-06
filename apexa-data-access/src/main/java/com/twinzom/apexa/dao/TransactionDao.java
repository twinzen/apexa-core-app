package com.twinzom.apexa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twinzom.apexa.dao.dto.TransactionDto;
import com.twinzom.apexa.dao.jpo.TxnMastPo;
import com.twinzom.apexa.dao.repository.TxnMastRepository;

@Component
public class TransactionDao {

	@Autowired 
	private TxnMastRepository txnMastRepository;
	
	public List<TransactionDto> getTxnsByAcctIds (List<Long> acids) {

		List<TxnMastPo> pos = txnMastRepository.findByAcid(acids);
		
		List<TransactionDto> dtos = new ArrayList<TransactionDto>();
		
		for (TxnMastPo po : pos) {
			TransactionDto dto = convertPoToDTo(po);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	private TransactionDto convertPoToDTo (TxnMastPo po) {
		
		TransactionDto dto = new TransactionDto();
		
		dto.setTxnid(po.getTxnid());
		dto.setTxnTypeCde(po.getTxnTypeCde());
		dto.setExtTxnRef(po.getExtTxnRef());
		dto.setExtTxnTypeCde(po.getExtTxnTypeCde());
		dto.setAcid(po.getAcid());
		dto.setSecid(po.getSecid());
		dto.setTrdDtTm(po.getTrdDtTm());
		dto.setTzdb(po.getTzdb());
		dto.setPostDtTm(po.getPostDtTm());
		dto.setExePrc(po.getExePrc());
		dto.setPrcCcy(po.getPrcCcy());
		dto.setQty(po.getQty());
		dto.setPripAmtLocl(po.getPripAmtLocl());
		dto.setSetlDtTm(po.getSetlDtTm());
		dto.setSetlCcy(po.getSetlCcy());
		dto.setSetlAmtSetl(po.getSetlAmtSetl());
		dto.setSetlLoclRate(po.getSetlLoclRate());
		dto.setMktCde(po.getMktCde());
		dto.setSrcSysCde(po.getSrcSysCde());
		dto.setTxnStat(po.getTxnStat());
		dto.setLongShtInd(po.getLongShtInd());
		dto.setMktValLocl(po.getMktValLocl());
		dto.setMktValAcct(po.getMktValAcct());
		dto.setMktValBase(po.getMktValBase());
		dto.setBkCostLocl(po.getBkCostLocl());
		dto.setBkCostAcct(po.getBkCostAcct());
		dto.setBkCostBase(po.getBkCostBase());
		dto.setExtLotRef(po.getExtLotRef());
		dto.setNarrTxt(po.getNarrTxt());
		dto.setEditRmkTxt(po.getEditRmkTxt());
		dto.setEditStafId(po.getEditStafId());
		
		return dto;
	}
	
}
