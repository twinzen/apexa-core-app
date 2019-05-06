//package com.twinzom.apexa.caleng.service;
//
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.twinzom.apexa.caleng.calculator.WeightedAverageCalculator;
//import com.twinzom.apexa.caleng.model.CalGroup;
//import com.twinzom.apexa.caleng.model.CalPosition;
//import com.twinzom.apexa.caleng.model.CalSnapshot;
//import com.twinzom.apexa.caleng.model.CalTxn;
//
//@Service
//public class CopyOfCalculateHoldingSnapshotsService {
//
//	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	
//	@Autowired
//	WeightedAverageCalculator waCalculator;
//	
//	public Map<String , CalSnapshot> process (List<CalGroup> calGroups, List<Date> dates) {
//		
//		// Sort the snapshot dates
//		Collections.sort(dates);
//		
//		Map<String, CalSnapshot> snapshots = new HashMap<String, CalSnapshot>();
//		
//		Date now = new Date(System.currentTimeMillis());
//		
//		for (CalGroup g : calGroups) {
//			
//			List<CalTxn> txns = g.getTxns();
//			
//			Collections.sort(txns);
//			
//			List<Date> validDates = new ArrayList<Date>();
//			
//			for (Date d : dates) {
//				if (txns.get(0).getTrdDtTm().before(d)) {
//					validDates.add(d);
//				}	
//			}
//			
//			CalPosition p = new CalPosition();
//			
//			for (int i=0; i<txns.size(); i++) {
//				
//				CalTxn t = txns.get(i);
//				
//				waCalculator.calculate(p, t);
//				
//				if (p.getQty().equals(BigDecimal.ZERO)) {
//					waCalculator.reset(p);
//				}
//				
//				if (validDates.size() >0
//						&& p != null
//						&& !p.getQty().equals(BigDecimal.ZERO)
//						&& txns.size() > i+1
//						&& txns.get(i+1).getTrdDtTm().after(validDates.get(0))) {
//					
//					takeSnapshot(snapshots, p, validDates.get(0));
//					validDates.remove(0);
//					
//				}	
//			}
//			
//			takeSnapshot(snapshots, p, now);
//			
//		}
//		
//		return snapshots;
//	}
//	
//	public void takeSnapshot (Map<String, CalSnapshot> snapshots, CalPosition p, Date d) {
//		
//		if (p == null)
//			return;
//		
//		String snapshotKey = String.valueOf(d.getTime());
//		
//		CalSnapshot snapshot = snapshots.get(snapshotKey);
//		
////		if (snapshot == null) {
////			snapshot = new CalSnapshot(d);
////			snapshots.put(snapshotKey, snapshot);
////		}
//		
//		snapshot.getPositions().add(p);
//	}
//	
//}
