package com.twinzom.apexa.tools.datageneration.ratehistory;

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

public class RateHistoryGenerator {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		//AWS - String myUrl = "jdbc:mysql://apexa.cfyzdksiv9zy.eu-west-2.rds.amazonaws.com:3306/apexa";
		String myUrl = "jdbc:mysql://35.239.120.213:3306/apexa";
		Class.forName("com.mysql.cj.jdbc.Driver");
		//AWS --Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		//dbConn.setAutoCommit(false);
		
		List<Date> weekDays = Util.getWeekDays(sdf.parse("2018-03-19"), sdf.parse("2019-04-02"));
		
		Map<String, Double> rateSeed = getRateSeed();
		
		List<RateHistory> rateHistorys = generate(weekDays, rateSeed);
		
		for (RateHistory rateHist : rateHistorys) {
			persistRateHistory(dbConn, rateHist);
			System.out.println(rateHist.getCcy()+","+sdf.format(rateHist.getRateDt())+" saved.");
		}
		
		//dbConn.commit();
			    
	}
	
	private static List<RateHistory> generate (List<Date> weekDays, Map<String, Double> rateSeed) {
		
		List<RateHistory> rateHistorys = new ArrayList<RateHistory>();
		
		for (Date d : weekDays) {
			for (String ccy : rateSeed.keySet()) {
				RateHistory rateHist = new RateHistory();
				rateHist.setCcy(ccy);
				rateHist.setRate(rateSeed.get(ccy) * (1+Util.randomChange(0.03)));
				rateHist.setRateDt(d);
				rateHistorys.add(rateHist);
			}
		}
		
		return rateHistorys;
		
	}
	
	private static void persistRateHistory (Connection dbConn, RateHistory rateHist) throws SQLException {
		
	      String query = " insert into rate_hist ("
	      		+ "ccy, "
	      		+ "rate, "
	      		+ "rate_dt )"
	        + " values (?,?,?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = dbConn.prepareStatement(query);
	      preparedStmt.setString(1, rateHist.getCcy());
	      preparedStmt.setDouble(2, rateHist.getRate());
	      Util.setPreparedStatmentDate(preparedStmt, 3, rateHist.getRateDt());
	  
	      preparedStmt.execute();
		
	}
	
	public static Map<String, Double> getRateSeed () throws SQLException {
		
		Map<String, Double> rateSeed = new HashMap<String, Double>();
		
		rateSeed.put("USD", 7.84990973);
		rateSeed.put("EUR", 8.82691 );
		rateSeed.put("AUD", 5.58616 );
		rateSeed.put("SGD", 5.80404 );
		rateSeed.put("GBP", 10.3373 );
		rateSeed.put("RMB", 1.17005 );
		rateSeed.put("NZD", 5.33381 );
		rateSeed.put("CAD", 5.90015 );
		rateSeed.put("CNY", 1.17005 );
		rateSeed.put("JPY", 0.0703964 );
		
		return rateSeed;
	}
	

}
