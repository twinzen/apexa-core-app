package com.twinzom.apexa.api.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.twinzom.apexa.api.model.Security;
import com.twinzom.apexa.dao.dto.SecurityDto;

@Component
public class SecurityMapper implements PoToModelMapper<SecurityDto, Security>{

	@Override
	public Security mapObject(SecurityDto dto) {
		
		Security s = new Security();
		s.setSecid(dto.getSecid());
		s.setCode(dto.getSecCdePri());
		s.setName(dto.getName());
		s.setCcy(dto.getLoclCcy());

		return s;
	}
	
	@Override
	public List<Security> mapObjects (List<SecurityDto> dtos) {
		
		List<Security> secs = new ArrayList<Security>();
		
		for (SecurityDto dto : dtos) {
			Security s = this.mapObject(dto);
			secs.add(s);
		}

		return secs;
	}
	
}
