package com.twinzom.apexa.caleng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twinzom.apexa.caleng.model.CalGroup;
import com.twinzom.apexa.caleng.model.CalPosition;
import com.twinzom.apexa.caleng.model.CalResponse;
import com.twinzom.apexa.caleng.model.CalSnapshot;
import com.twinzom.apexa.caleng.service.CalculateHoldingSnapshotsService;

@RestController
public class CalEngController {

	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	CalculateHoldingSnapshotsService calculateHoldingSnapshotsService;
	
	@RequestMapping(value="/calculateHoldingSnapshots")
    public CalResponse calculateHoldingSnapshots (
    		@RequestParam(value="dates") String datesStr,
    		@RequestBody List<CalGroup> calGroups) throws ParseException {
		
		List<Date> dates = new ArrayList<Date>();
		dates.add(sdf.parse(datesStr));
		
		Map<String , List<CalSnapshot>> snapshotsByGroup = calculateHoldingSnapshotsService.process(calGroups, dates);
		
		CalResponse resp = new CalResponse();
		resp.setSnapshotsByGroup(snapshotsByGroup);
		
		return resp;
		
    }	
	
}
