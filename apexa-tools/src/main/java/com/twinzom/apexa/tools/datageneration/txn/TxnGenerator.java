package com.twinzom.apexa.tools.datageneration.txn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinzom.apexa.tools.common.Util;
import com.twinzom.apexa.tools.datageneration.pricehistory.PriceHistory;
import com.twinzom.apexa.tools.datageneration.pricehistory.SecurityPrice;
import com.twinzom.apexa.tools.datageneration.ratehistory.RateHistory;

public class TxnGenerator {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat txnRefSdf = new SimpleDateFormat("yyyyMMdd");
	static Double CHARGE = 0.05;
	static String SQL_FILE = "/Users/twinsen/Projects/txn_mst.txt";
	static String CSV_FILE = "/Users/twinsen/Projects/txn_mst.csv";
	static String PERSIST_SQL = " insert into txn_mast ("
      		+ "txn_type_cde, "
      		+ "ext_txn_ref, "
      		+ "ext_txn_type_cde, "
      		+ "acid, "
      		+ "secid, "
      		+ "exe_dt_tm, "
      		+ "post_dt_tm, "
      		+ "exe_prc, "
      		+ "prc_ccy, "
      		+ "qty, "
      		+ "prip_amt_locl, "
      		+ "setl_dt_tm, "
      		+ "setl_ccy, "
      		+ "setl_amt_setl, "
      		+ "setl_locl_rate, "
      		+ "mkt_cde, "
      		+ "src_sys_cde, "
      		+ "cfm_ind, "
      		+ "long_sht_ind, "
      		+ "mkt_val_locl, "
      		+ "mkt_val_acct, "
      		+ "bk_cost_locl, "
      		+ "bk_cost_acct )"
        + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		//AWS - String myUrl = "jdbc:mysql://apexa.cfyzdksiv9zy.eu-west-2.rds.amazonaws.com:3306/apexa";
		String myUrl = "jdbc:mysql://35.239.120.213:3306/apexa";
		Class.forName("com.mysql.cj.jdbc.Driver");
		//AWS --Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		//dbConn.setAutoCommit(false);

		Map<String, Map<String, PriceHistory>> priceHistorys = getPriceHistorys(dbConn);
		
		Map<String, Map<String, RateHistory>> rateHistorys = getRateHistorys(dbConn);
		
		List<String> secCdes = new ArrayList<String>(priceHistorys.keySet());
		
		List<Date> weekDays = Util.getWeekDays(sdf.parse("2018-03-19"), sdf.parse("2019-04-02"));
		
		List<Transaction> txns = new ArrayList<Transaction>();
		
		for (Long acid=1L; acid <= 5000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".0", txns);
		
		txns.clear();
		
		for (Long acid=5001L; acid <= 10000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".1", txns);
		
		txns.clear();
		
		for (Long acid=10001L; acid <= 15000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".2", txns);
		
		txns.clear();
		
		for (Long acid=15001L; acid <= 20000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".4", txns);
		
		txns.clear();
		
		for (Long acid=20001L; acid <= 25000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".5", txns);
		
		txns.clear();
		
		for (Long acid=25001L; acid <= 30000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".6", txns);
		
		txns.clear();
		
		for (Long acid=30001L; acid <= 35000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".7", txns);
		
		txns.clear();
		
		for (Long acid=35001L; acid <= 40000L; acid++) {
			txns.addAll(generate(acid, secCdes, weekDays, priceHistorys, rateHistorys, 20, 50));
			System.out.println(acid + "done.");
		}
		csvToFile(CSV_FILE+".8", txns);
		
		txns.clear();
		
		//sqlToFile(SQL_FILE, txns);
		
		
		//ObjectMapper om = new ObjectMapper();
		
		//System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(txns));
		
//		for (Transaction t : txns) {
//			persistTxn(dbConn, t);
//		}
		
		
		
	}
	
	private static void sqlToFile (String sqlFile, List<Transaction> txns) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(sqlFile));
		for (Transaction t : txns) {
			String sql = PERSIST_SQL;
			sql = sql.replaceFirst("\\?", "'"+t.getTxnTypeCde()+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getExtTxnRef()+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getExtTxnTypeCde()+"'");
			sql = sql.replaceFirst("\\?", ""+t.getAcid());
			sql = sql.replaceFirst("\\?", ""+t.getSecid());
			sql = sql.replaceFirst("\\?", "'"+sdf.format(t.getExeDtTm())+"'");
			sql = sql.replaceFirst("\\?", "'"+sdf.format(t.getPostDtTm())+"'");
			sql = sql.replaceFirst("\\?", ""+t.getExePrc());
			sql = sql.replaceFirst("\\?", "'"+t.getPrcCcy()+"'");
			sql = sql.replaceFirst("\\?", ""+t.getQty());
			sql = sql.replaceFirst("\\?", ""+t.getPripAmtLocl());
			sql = sql.replaceFirst("\\?", "'"+sdf.format(t.getSetlDtTm())+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getSetlCcy()+"'");
			sql = sql.replaceFirst("\\?", ""+t.getSetlAmtSetl());
			sql = sql.replaceFirst("\\?", ""+t.getSetlLoclRate());
			sql = sql.replaceFirst("\\?", "'"+t.getMktCde()+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getSrcSysCde()+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getCfmInd()+"'");
			sql = sql.replaceFirst("\\?", "'"+t.getLongShtInd()+"'");
			sql = sql.replaceFirst("\\?", ""+t.getMktValLocl());
			sql = sql.replaceFirst("\\?", ""+t.getMktValAcct());
			sql = sql.replaceFirst("\\?", ""+t.getBkCostLocl());
			sql = sql.replaceFirst("\\?", ""+t.getBkCostAcct());
			sql += ";\n";
			writer.write(sql);
			//System.out.println(sql);
		}
		writer.flush();
		writer.close();
	}
	
	private static void csvToFile (String csvFile, List<Transaction> txns) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
		for (Transaction t : txns) {
			String csv = "";
			csv += t.getTxnTypeCde()+",";
			csv += t.getExtTxnRef()+",";
			csv += t.getExtTxnTypeCde()+",";
			csv += t.getAcid()+",";
			csv += t.getSecid()+",";
			csv += sdf.format(t.getExeDtTm())+",";
			csv += sdf.format(t.getPostDtTm())+",";
			csv += t.getExePrc()+",";
			csv += t.getPrcCcy()+",";
			csv += t.getQty()+",";
			csv += t.getPripAmtLocl()+",";
			csv += sdf.format(t.getSetlDtTm())+",";
			csv += t.getSetlCcy()+",";
			csv += t.getSetlAmtSetl()+",";
			csv += t.getSetlLoclRate()+",";
			csv += t.getMktCde()+",";
			csv += t.getSrcSysCde()+",";
			csv += t.getCfmInd()+",";
			csv += t.getLongShtInd()+",";
			csv += t.getMktValLocl()+",";
			csv += t.getMktValAcct()+",";
			csv += t.getBkCostLocl()+",";
			csv += t.getBkCostAcct()+",";
			csv += "\n";
			writer.write(csv);
			//System.out.println(sql);
		}
		writer.flush();
		writer.close();
	}
	
	private static void persistTxn (Connection dbConn, Transaction t) throws SQLException {
		
	      String sql = PERSIST_SQL;

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = dbConn.prepareStatement(sql);
	      preparedStmt.setString(1, t.getExtTxnTypeCde());
	      preparedStmt.setString(2, t.getExtTxnRef());
	      preparedStmt.setString(3, t.getExtTxnTypeCde());
	      preparedStmt.setLong(4, t.getAcid());
	      preparedStmt.setLong(5, t.getSecid());
	      Util.setPreparedStatmentDate(preparedStmt, 6, t.getExeDtTm());
	      Util.setPreparedStatmentDate(preparedStmt, 7, t.getPostDtTm());
	      Util.setPreparedStatmentDouble(preparedStmt, 8, t.getExePrc());
	      preparedStmt.setString(9, t.getPrcCcy());
	      Util.setPreparedStatmentDouble(preparedStmt, 10, t.getQty());
	      Util.setPreparedStatmentDouble(preparedStmt, 11, t.getPripAmtLocl());
	      Util.setPreparedStatmentDate(preparedStmt, 12, t.getSetlDtTm());
	      preparedStmt.setString(13, t.getSetlCcy());
	      Util.setPreparedStatmentDouble(preparedStmt, 14, t.getSetlAmtSetl());
	      Util.setPreparedStatmentDouble(preparedStmt, 15, t.getSetlLoclRate());
	      preparedStmt.setString(16, t.getMktCde());
	      preparedStmt.setString(17, t.getSrcSysCde());
	      preparedStmt.setString(18, t.getCfmInd());
	      preparedStmt.setString(19, t.getLongShtInd());
	      Util.setPreparedStatmentDouble(preparedStmt, 20, t.getMktValLocl());
	      Util.setPreparedStatmentDouble(preparedStmt, 21, t.getMktValAcct());
	      Util.setPreparedStatmentDouble(preparedStmt, 22, t.getBkCostLocl());
	      Util.setPreparedStatmentDouble(preparedStmt, 23, t.getBkCostAcct());
	      
	      preparedStmt.execute();
		
	}
	
	public static List<Transaction> generate (
				Long acid,
				List<String> secCdes,
				List<Date> weekDays,
				Map<String, Map<String, PriceHistory>> priceHistorys,
				Map<String, Map<String, RateHistory>> rateHistorys,
				int numOfHolding,
				int numOfTxn
			) {
		
		List<String> secPicked = pickSecs (secCdes, numOfHolding);
		
		List<Transaction> txns = new ArrayList<Transaction>();
		
		int seq = 0;
		
		for (String secCde : secPicked) {
			
			List<Date> datePicked = pickDates(weekDays, numOfTxn);
			double totalQty = 0;
			int buySellHalf = 0;
			
			for (Date d : datePicked) {
				
				if(totalQty == 0) 
					buySellHalf = 0;
				else
					buySellHalf = pickBuySelHalf();

				PriceHistory prcHist = priceHistorys.get(secCde).get(sdf.format(d));
				String ccy = prcHist.getCcy();
				double price = prcHist.getPrc();
				Long secid = prcHist.getSecid();
				
				//System.out.println(ccy+" "+sdf.format(d));
				
				double rate = 1;
				if (!ccy.equals("HKD")) {
					rate = rateHistorys.get(ccy).get(sdf.format(d)).getRate();
				}
				
				String txnRef = "T"+txnRefSdf.format(new Date(System.currentTimeMillis()))+"-"+seq;
				
				switch (buySellHalf) {
					case 0:
						double qty = pickQty();
						txns.add(generateBuy(acid, txnRef, d, secid, rate, price, ccy, qty));
						totalQty += qty;
						break;
					case 1:
						txns.add(generateSell(acid, txnRef, d, secid, rate, price, ccy, totalQty));
						totalQty = 0;
						break;
					case 2:
						txns.add(generateSell(acid, txnRef, d, secid, rate, price, ccy, totalQty/2));
						totalQty = totalQty/2;
						break;
					default:
				}
				seq++;
			}
		}
		
		return txns;
		
	}
	
	public static Transaction generateBuy (Long acid, String txnRef, Date d, Long secid, double rate, double price, String ccy, double qty) {
		
		Transaction txn = new Transaction();
		double prip = qty*price;
		double bkcost = qty * price * (1+CHARGE);
		
		txn.setTxnTypeCde("SI");
		txn.setExtTxnRef(txnRef);
		txn.setExtTxnTypeCde("BUY");
		txn.setAcid(acid);
		txn.setSecid(secid);
		txn.setExeDtTm(d);
		txn.setPostDtTm(new Date(System.currentTimeMillis()));
		txn.setExePrc(price);
		txn.setPrcCcy(ccy);
		txn.setQty(qty);
		txn.setPripAmtLocl(prip);
		txn.setSetlDtTm(d);
		txn.setSetlCcy("HKD");
		txn.setSetlAmtSetl(bkcost*rate);
		txn.setSetlLoclRate(rate);
		txn.setMktCde("HK");
		txn.setSrcSysCde("PATCH");
		txn.setCfmInd("S");
		txn.setLongShtInd("L");
		txn.setMktValLocl(prip);
		txn.setMktValAcct(prip*rate);
		txn.setBkCostLocl(bkcost);
		txn.setBkCostAcct(bkcost*rate);
		
		return txn;
		
	}
	
public static Transaction generateSell (Long acid, String txnRef, Date d, Long secid, double rate, double price, String ccy, double qty) {
		
		Transaction txn = new Transaction();
		double prip = qty*price;
		double bkcost = qty * price * (1-CHARGE);
		
		txn.setTxnTypeCde("SOPL");
		txn.setExtTxnRef(txnRef);
		txn.setExtTxnTypeCde("SELL");
		txn.setAcid(acid);
		txn.setSecid(secid);
		txn.setExeDtTm(d);
		txn.setPostDtTm(new Date(System.currentTimeMillis()));
		txn.setExePrc(price);
		txn.setPrcCcy(ccy);
		txn.setQty(qty);
		txn.setPripAmtLocl(prip);
		txn.setSetlDtTm(d);
		txn.setSetlCcy("HKD");
		txn.setSetlAmtSetl(bkcost*rate);
		txn.setSetlLoclRate(rate);
		txn.setMktCde("HK");
		txn.setSrcSysCde("PATCH");
		txn.setCfmInd("S");
		txn.setLongShtInd("L");
		txn.setMktValLocl(prip);
		txn.setMktValAcct(prip*rate);
		txn.setBkCostLocl(bkcost);
		txn.setBkCostAcct(bkcost*rate);
		
		return txn;
		
	}
	
	public static int pickBuySelHalf () {
		int num = (int) (Math.random() * 21);
		
		if (num >= 19)
			return 2;
		else if (num >= 17)
			return 1;
		else 
			return 0;
	}
	
	public static List<String> pickSecs (List<String> secCdes, int numOfHolding) {
		List<String> secPicked = new ArrayList<String>();
		
		while (secPicked.size() < numOfHolding) {
			int index = (int) (Math.random() * secCdes.size());
			if (!secPicked.contains(secCdes.get(index))) {
				secPicked.add(secCdes.get(index));
			}
		}
		
		Collections.sort(secPicked);
		
		return secPicked;
	}
	
	public static double pickQty () {
		
		return ((int) (Math.random() * 20) + 5) * 1000;
	}
	
	
	public static List<Date> pickDates (List<Date> weekDays, int numOfDate) {
		
		List<Date> datePicked = new ArrayList<Date>();
		
		while (datePicked.size() < numOfDate) {
			
			int index = (int) (Math.random() * weekDays.size());
			if (!datePicked.contains(weekDays.get(index))) {
				datePicked.add(weekDays.get(index));
			}
		}
		
		Collections.sort(datePicked);
		
		return datePicked;
		
	}
	
	public static Map<String, Map<String, PriceHistory>> getPriceHistorys (Connection dbConn) throws SQLException {
		
		Map<String, Map<String, PriceHistory>> priceHistorys = new HashMap<String, Map<String, PriceHistory>>();
		
		Statement stmt = dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from sec_prc_hist");
		while (rs.next()) {
		  PriceHistory prcHist = new PriceHistory();
		  prcHist.setSecid(rs.getLong("secid"));
		  prcHist.setSecCde(rs.getString("sec_cde"));
		  prcHist.setCcy(rs.getString("ccy"));
		  prcHist.setPrc(rs.getDouble("prc"));
		  prcHist.setPrcDt(rs.getDate("prc_dt"));
		  
		  Map<String, PriceHistory> prcMap = priceHistorys.get(prcHist.getSecCde());
			if (prcMap == null) {
				prcMap = new HashMap<String, PriceHistory>();
				priceHistorys.put(prcHist.getSecCde(), prcMap);
			}
			
			prcMap.put(sdf.format(prcHist.getPrcDt()), prcHist);
		  
		}
		
		return priceHistorys;

	}
	
	public static Map<String, Map<String, RateHistory>> getRateHistorys (Connection dbConn) throws SQLException {
		
		Map<String, Map<String, RateHistory>> rateHistorys = new HashMap<String, Map<String, RateHistory>>();
		
		Statement stmt = dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from rate_hist");
		while (rs.next()) {
			RateHistory rateHist = new RateHistory();
			rateHist.setCcy(rs.getString("ccy"));
			rateHist.setRate(rs.getDouble("rate"));
			rateHist.setRateDt(rs.getDate("rate_dt"));
			
			Map<String, RateHistory> ccyMap = rateHistorys.get(rateHist.getCcy());
			if (ccyMap == null) {
				ccyMap = new HashMap<String, RateHistory>();
				rateHistorys.put(rateHist.getCcy(), ccyMap);
			}
			
			ccyMap.put(sdf.format(rateHist.getRateDt()), rateHist);
		}
		
		return rateHistorys;

	}
 	
}
