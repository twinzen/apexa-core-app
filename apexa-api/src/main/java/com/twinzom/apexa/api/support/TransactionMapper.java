package com.twinzom.apexa.api.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.twinzom.apexa.api.model.Transaction;
import com.twinzom.apexa.dao.dto.TransactionDto;

@Component
public class TransactionMapper implements PoToModelMapper<TransactionDto, Transaction>{

	@Override
	public Transaction mapObject(TransactionDto po) {
		
		Transaction t = new Transaction();
		
		t.setTxnid(po.getTxnid());
		t.setTxnTypeCde(po.getTxnTypeCde());
		t.setExtTxnRef(po.getExtTxnRef());
		t.setExtTxnTypeCde(po.getExtTxnTypeCde());
		t.setSecid(po.getSecid());
		t.setTrdDtTm(po.getTrdDtTm());
		t.setExePrc(po.getExePrc());
		t.setPrcCcy(po.getPrcCcy());
		t.setQty(po.getQty());
		t.setPripAmtLocl(po.getPripAmtLocl());
		t.setSetlDtTm(po.getSetlDtTm());
		t.setSetlCcy(po.getSetlCcy());
		t.setSetlAmtSetl(po.getSetlAmtSetl());
		t.setSetlLoclRate(po.getSetlLoclRate());
		t.setMktCde(po.getMktCde());
		t.setLongShtInd(po.getLongShtInd());
		t.setMktValLocl(po.getMktValLocl());
		t.setMktValAcct(po.getMktValAcct());
		t.setBkCostLocl(po.getBkCostLocl());
		t.setBkCostAcct(po.getBkCostAcct());
		
		return null;
	}
	
	@Override
	public List<Transaction> mapObjects (List<TransactionDto> txnDtos) {
		
		List<Transaction> txns = new ArrayList<Transaction>();
		
		for (TransactionDto po : txnDtos) {
			Transaction t = this.mapObject(po);
			txns.add(t);
		}

		return txns;
	}

}
