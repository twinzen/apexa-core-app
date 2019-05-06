package com.twinzom.apexa.api.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twinzom.apexa.api.model.Security;
import com.twinzom.apexa.api.model.SecurityContainer;
import com.twinzom.apexa.dao.SecurityDao;
import com.twinzom.apexa.dao.dto.SecurityDto;

@Component
public class SecurityPopulator <T extends SecurityContainer> {
	
	@Autowired
	SecurityDao securityDao;

	@Autowired
	SecurityMapper securityMapper;
	
	public void populate (SecurityContainer secCtnr) {
				
		Long secid = secCtnr.getSecid();
		
		SecurityDto dto = securityDao.getSecsBySecid(secid);
		
		Security sec = securityMapper.mapObject(dto);
		
		secCtnr.setSecurity(sec);
		
	}
	
	public void populateMultiple (List<SecurityContainer> secCtnrs) {
		
		List<Long> secids = new ArrayList<Long>();
		
		for (SecurityContainer secCtnr : secCtnrs) {
			secids.add(secCtnr.getSecid());
		}
		
		List<SecurityDto> dtos = securityDao.getSecsBySecids(secids);
		
		Map<Long, Security> secs = new HashMap<Long, Security>();
		
		for (SecurityDto dto : dtos) {
			Security sec = securityMapper.mapObject(dto);
			secs.put(sec.getSecid(), sec);
		}
		
		for (SecurityContainer txn : secCtnrs) {
			Security s = secs.get(txn.getSecid());
			txn.setSecurity(s);
		}
		
	}
	
	
}
