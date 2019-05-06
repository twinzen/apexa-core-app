package com.twinzom.apexa.tools.datageneration.pricehistory;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twinzom.apexa.tools.common.Util;
import com.twinzom.apexa.tools.datafetch.fundexpress.Fund;

public class PriceHistoryGenerator {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String SQL_FILE = "/Users/twinsen/Projects/prcHist.txt";
	static String PERSIST_SQL = " insert into sec_prc_hist ("
      		+ "secid, "
      		+ "sec_cde, "
      		+ "ccy, "
      		+ "prc, "
      		+ "prc_dt )"
        + " values (?,?,?,?,?)";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		String myUrl = "jdbc:mysql://35.239.120.213:3306/apexa";
		Class.forName("com.mysql.cj.jdbc.Driver");
		//AWS --Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		//dbConn.setAutoCommit(false);
		dbConn.setAutoCommit(false);
		
		Map<String, SecurityPrice> priceSeed = getPriceSeed(dbConn);
		
		List<Date> weekDays = Util.getWeekDays(sdf.parse("2018-03-19"), sdf.parse("2019-04-02"));
		
		List<PriceHistory> priceHistorys = generate(weekDays, priceSeed);
		
//		for (PriceHistory prcHist : priceHistorys) {
//			persistPriceHistory(dbConn, prcHist);
//			System.out.println(prcHist.getSecCde()+","+sdf.format(prcHist.getPrcDt())+" saved.");
//		}
//		dbConn.commit();
		
		sqlToFile(SQL_FILE, priceHistorys);
	    
	}
	
	private static List<PriceHistory> generate (List<Date> weekDays, Map<String, SecurityPrice> priceSeed) {
		
		List<PriceHistory> priceHistorys = new ArrayList<PriceHistory>();
		
		for (Date d : weekDays) {
			for (SecurityPrice secPrc : priceSeed.values()) {
				PriceHistory prcHist = new PriceHistory();
				prcHist.setSecid(secPrc.getSecid());
				prcHist.setSecCde(secPrc.getSecCde());
				prcHist.setCcy(secPrc.getCcy());
				prcHist.setPrc(secPrc.getPrc() * (1+Util.randomChange(0.03)));
				prcHist.setPrcDt(d);
				priceHistorys.add(prcHist);
			}
		}
		
		return priceHistorys;
		
	}
	
	private static void sqlToFile (String sqlFile, List<PriceHistory> priceHistorys) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(sqlFile));
		for (PriceHistory prcHist : priceHistorys) {
			String sql = PERSIST_SQL;
			sql = sql.replaceFirst("\\?", ""+prcHist.getSecid());
			sql = sql.replaceFirst("\\?", "'"+prcHist.getSecCde()+"'");
			sql = sql.replaceFirst("\\?", "'"+prcHist.getCcy()+"'");
			sql = sql.replaceFirst("\\?", ""+prcHist.getPrc());
			sql = sql.replaceFirst("\\?", "'"+sdf.format(prcHist.getPrcDt())+"'");
			writer.write(sql);
			writer.write(";");
			writer.write("\n");
			System.out.println(sql);
		}
		writer.flush();
		writer.close();
	}
	
	private static void persistPriceHistory (Connection dbConn, PriceHistory prcHist) throws SQLException {
		
	      String sql = PERSIST_SQL;

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = dbConn.prepareStatement(sql);
	      preparedStmt.setLong(1, prcHist.getSecid());
	      preparedStmt.setString(2, prcHist.getSecCde());
	      preparedStmt.setString(3, prcHist.getCcy());
	      preparedStmt.setDouble(4, prcHist.getPrc());
	      Util.setPreparedStatmentDate(preparedStmt, 5, prcHist.getPrcDt());
	  
	      preparedStmt.execute();
		
	}
	
	public static Map<String, SecurityPrice> getPriceSeed (Connection dbConn) throws SQLException {
		
		Map<String, SecurityPrice> priceSeed = new HashMap<String, SecurityPrice>();
		
		Statement stmt = dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from sec_mast where stat_cde = 'A'");
		while (rs.next()) {
		  SecurityPrice secPrc = new SecurityPrice();
		  secPrc.setSecid(rs.getLong("secid"));
		  secPrc.setSecCde(rs.getString("secCde"));
		  secPrc.setPrc(rs.getDouble("nav_prc"));
		  secPrc.setCcy(rs.getString("ccy"));
		  priceSeed.put(secPrc.getSecCde(), secPrc);
		}
		
		return priceSeed;
	}
	

}
