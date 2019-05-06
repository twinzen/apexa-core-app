package com.twinzom.apexa.caleng.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.twinzom.apexa.caleng.model.CalPosition;
import com.twinzom.apexa.caleng.model.CalTxn;

@Component
public class WeightedAverageCalculator {

	public void calculate (CalPosition p, CalTxn t) {
		
		BigDecimal newQty = BigDecimal.ZERO;
		BigDecimal newBkCostLocl = BigDecimal.ZERO;
		BigDecimal newBkCostAcct = BigDecimal.ZERO;
		BigDecimal newIncome = BigDecimal.ZERO;
		Date newStartDate = null;
		Date newEndDate = null;
		
		switch(t.getTxnTypeCde()) {
			case "SI":
				
				newQty = p.getQty().add(t.getQty());
				newBkCostLocl = p.getBkCostLocl().add(t.getBkCostLocl());
				newBkCostAcct = p.getBkCostAcct().add(t.getBkCostAcct());
				break;
			
			case "SO":
				
				newQty = p.getQty().subtract(t.getQty());
				newBkCostLocl = p.getBkCostLocl().multiply(newQty.divide(p.getQty(), 8, RoundingMode.HALF_EVEN));
				newBkCostAcct = p.getBkCostAcct().multiply(newQty.divide(p.getQty(), 8, RoundingMode.HALF_EVEN));
				break;
				
			case "SOPL":
				
				newQty = p.getQty().subtract(t.getQty());
				newBkCostLocl = p.getBkCostLocl().multiply(newQty.divide(p.getQty(), 8, RoundingMode.HALF_EVEN));
				newBkCostAcct = p.getBkCostAcct().multiply(newQty.divide(p.getQty(), 8, RoundingMode.HALF_EVEN));
				
				BigDecimal rglLocl = t.getBkCostLocl().subtract(p.getBkCostLocl().multiply(t.getQty().divide(p.getQty(), 8, RoundingMode.HALF_EVEN)));
				BigDecimal rglAcct = t.getBkCostAcct().subtract(p.getBkCostAcct().multiply(t.getQty().divide(p.getQty(), 8, RoundingMode.HALF_EVEN)));
				t.setRglLocl(rglLocl);
				t.setRglAcct(rglAcct);
				break;
			
			default:	
		}
		
		p.setQty(newQty);
		p.setBkCostLocl(newBkCostLocl);
		p.setBkCostAcct(newBkCostAcct);
		p.setIncome(newIncome);
		
	}
	
	public void reset (CalPosition p) {
		
		p.setQty(BigDecimal.ZERO);
		p.setBkCostLocl(BigDecimal.ZERO);
		p.setBkCostAcct(BigDecimal.ZERO);
		p.setIncome(BigDecimal.ZERO);
		p.setStartDate(null);
		p.setEndDate(null);
		p.setValDate(null);
		
	}
	
}
