package com.twinzom.apexa.tools.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {


	public static void setPreparedStatmentInt (PreparedStatement preparedStmt, int parameterIndex, Integer value) throws SQLException {
		if (value != null) {
			preparedStmt.setInt(parameterIndex, value);
		} else {
			preparedStmt.setNull(parameterIndex, Types.INTEGER);
		}
	}
	
	public static void setPreparedStatmentDouble (PreparedStatement preparedStmt, int parameterIndex, Double value) throws SQLException {
		if (value != null) {
			preparedStmt.setDouble(parameterIndex, value);
		} else {
			preparedStmt.setNull(parameterIndex, Types.DECIMAL);
		}
	}
	
	public static void setPreparedStatmentDate (PreparedStatement preparedStmt, int parameterIndex, Date value) throws SQLException {
		if (value != null) {
			preparedStmt.setDate(parameterIndex, new java.sql.Date(value.getTime()));
		} else {
			preparedStmt.setNull(parameterIndex, Types.DATE);
		}
	}
	
	public static Double parseToDouble(String str) {
	    Double value = null;
	    try {
	        value = Double.parseDouble(str);
	    }
	    catch(NumberFormatException e) {
	        // Do something
	    }
	    return value;
	}
	
	public static Date parseToDate(String str, SimpleDateFormat sdf) {
	    Date value = null;
	    try {
	        value = sdf.parse(str);
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return value;
	}
	
	public static List<Date> getWeekDays (Date start, Date end) {
		
		List<Date> weekDays= new ArrayList<Date>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		
		while (c.getTime().before(end) || c.getTime().equals(end)) {
			
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek > 1 && dayOfWeek < 7) {
				weekDays.add(c.getTime());
			}
			
			c.add(Calendar.DATE, 1);
			
		}
		
		return weekDays;
	}
	
	public static double randomChange (double limit) {
		
		double signNum = Math.random();
		int sign = (signNum > 0.5)? -1 : 1;
		
		return Math.random() * limit * sign;
		
	}
	
}
