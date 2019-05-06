package com.twinzom.apexa.caleng.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinzom.apexa.caleng.calculator.WeightedAverageCalculator;
import com.twinzom.apexa.caleng.model.CalGroup;
import com.twinzom.apexa.caleng.model.CalPosition;
import com.twinzom.apexa.caleng.model.CalSnapshot;
import com.twinzom.apexa.caleng.model.CalTxn;

@Service
public class CalculateHoldingSnapshotsService {

	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	WeightedAverageCalculator waCalculator;
	
	public Map<String , List<CalSnapshot>> process (List<CalGroup> calGroups, List<Date> dates) throws ParseException {
		
		// Sort the snapshot dates
		Collections.sort(dates);
		
		Map<String, List<CalSnapshot>> snapshotsByGroup = new HashMap<String, List<CalSnapshot>>();
		
		Date now = new Date(System.currentTimeMillis());
		
		for (CalGroup g : calGroups) {
			
			List<CalSnapshot> snapshots = new ArrayList<CalSnapshot>();
	
			snapshotsByGroup.put(g.getGroupId(), snapshots);
			
			//System.out.println("****************"+g.getGroupId());
			
			List<CalTxn> txns = g.getTxns();
			
			Collections.sort(txns);
			
			List<Date> validDates = new ArrayList<Date>();
			
			for (Date d : dates) {
				if (txns.get(0).getTrdDtTm().before(d)) {
					validDates.add(d);
				}	
			}
			
			CalPosition p = new CalPosition();
			
			for (int i=0; i<txns.size(); i++) {
				
				CalTxn t = txns.get(i);
				
				waCalculator.calculate(p, t);
				
				if (p.getQty().equals(BigDecimal.ZERO)) {
					waCalculator.reset(p);
				}
				
				if (validDates.size() >0
						&& p != null
						&& txns.size() > i+1
						&& txns.get(i+1).getTrdDtTm().after(toDateEnd(validDates.get(0)))) {
					
					//System.out.println("Take Snapshot 1: "+validDates.get(0)+", "+p.getQty());
					takeSnapshot(snapshots, p, validDates.get(0));
					validDates.remove(0);
					
				}	
			}
			
			if (validDates.size() >0
					&& p != null
					&& !p.getQty().equals(BigDecimal.ZERO)) {
				for (Date d : validDates) {
					//System.out.println("Take Snapshot 2: "+d+", "+p.getQty());
					takeSnapshot(snapshots, p, d);
				}
			}
			
			//System.out.println("Take Snapshot 3: "+now+", "+p.getQty());
			takeSnapshot(snapshots, p, now);
			
		}
		
		return snapshotsByGroup;
	}
	
	public void takeSnapshot (List<CalSnapshot> snapshots, CalPosition p, Date d) {
		
		if (p == null)
			return;
		
		Date dateEnd = null;
		try {
			dateEnd = toDateEnd(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String snapshotKey = sdf.format(dateEnd);
		
		CalSnapshot snapshot = new CalSnapshot();
		
		snapshot.setSnapshotKey(snapshotKey);
		snapshot.setPosition(new CalPosition(p));
		
		snapshots.add(snapshot);
		
	}
	
	private Date toDateEnd (Date d) throws ParseException {
		return sdf2.parse(sdf.format(d)+" 23:59:59");
	}
	
}
