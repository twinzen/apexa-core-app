package com.twinzom.apexa.tools.datafetch.fundexpress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinzom.apexa.tools.common.Util;

public class FundExpressFectcher {

	static final String FUND_EXPRESS_URL = "http://www.fundexpress.hsbc.com.hk/HSBCSite/Overview.aspx?code=";
	static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	static final ObjectMapper jsonMapper = new ObjectMapper();
	
	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		
		// create a mysql database connection
		//AWS - String myUrl = "jdbc:mysql://apexa.cfyzdksiv9zy.eu-west-2.rds.amazonaws.com:3306/apexa";
		String myUrl = "jdbc:mysql://35.239.120.213:3306/apexa";
		Class.forName("com.mysql.cj.jdbc.Driver");
		//AWS --Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		Connection dbConn = DriverManager.getConnection(myUrl, "apexa", "apexa.123");
		//dbConn.setAutoCommit(false);
	    
		List<String> fundCodes = getFundCodes();
		
		for (String fundCode : fundCodes) {
			Fund fund = fetch(fundCode);
			persistFund(dbConn, fund);
			System.out.println(fundCode + " saved.");
		}
		
		//System.out.println(jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fund));
		
		

	}
	
	private static Fund fetch (String fundCode) throws MalformedURLException, IOException {
		
		Fund fund = new Fund();
		
		String html = null;
		
		URLConnection conn = new URL(FUND_EXPRESS_URL+fundCode).openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line;
		
		while ((line = br.readLine()) != null) {
			html += line;
		}
		
		br.close();
		
		Document doc = Jsoup.parse(html);
		
		String nameHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbFundNameText").html();
		String riskLvlHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbRiskLevelText").html();
		String deviationHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbStdDeviationText").html();
		String annualReturnHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbReturn3YearText").html();
		String sharpeHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbSharpeRatioText").html();
		String navPriceHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbNAVText").html();
		String bidPriceHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbBidText").html();
		String offerPriceHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbOfferText").html();
		String min52PriceHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbMinPriceText").html();
		String max52PriceHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbMaxPriceText").html();
		String ccyHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbFundCurrencyText").html();
		String fundHouseHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbFundHouseText").html();
		String priceDateHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbPriceDateText").html();
		String statisticDateHtml = doc.getElementById("ctl00_ContentPlaceHolder1_lbRiskDateText").html();
		Element statusElement = doc.getElementById("ctl00_ContentPlaceHolder1_lbStatusText");
		
		fund.setFundCode(fundCode);
		fund.setName(nameHtml);
		fund.setRiskLvl(Integer.parseInt(riskLvlHtml));
		fund.setDeviation(Util.parseToDouble(deviationHtml.replace("%", "")));
		fund.setAnnualReturn(Util.parseToDouble(annualReturnHtml.replace("%", "")));
		fund.setSharpe(Util.parseToDouble(sharpeHtml));
		fund.setNavPrice(Util.parseToDouble(navPriceHtml));
		fund.setBidPrice(Util.parseToDouble(bidPriceHtml));
		fund.setOfferPrice(Util.parseToDouble(offerPriceHtml));
		fund.setMin52Price(Util.parseToDouble(min52PriceHtml));
		fund.setMax52Price(Util.parseToDouble(max52PriceHtml));
		fund.setCcy(ccyHtml);
		fund.setFundHouse(fundHouseHtml);
		fund.setPriceDate(Util.parseToDate(priceDateHtml.replace("(as of ","").replace(")",""), sdf));
		fund.setStatisticDate(Util.parseToDate(statisticDateHtml.replace("(as of ","").replace(")",""), sdf));
		if (statusElement == null) {
			fund.setStatusCode("A");
		} else {
			fund.setStatusCode("C");
		}
		
		
		return fund;
		
	}
	
	private static void persistFund (Connection dbConn, Fund fund) throws SQLException {
		
	      String query = " insert into sec_mast ("
	      		+ "secCde, "
	      		+ "name, "
	      		+ "ccy, "
	      		+ "risk_lvl, "
	      		+ "devi, "
	      		+ "ann_rtrn, "
	      		+ "sharpe, "
	      		+ "nav_prc, "
	      		+ "bid_prc, "
	      		+ "off_prc, "
	      		+ "min_52_prc, "
	      		+ "max_52_prc, "
	      		+ "prc_dt, "
	      		+ "statc_dt, "
	      		+ "fund_hse, "
	      		+ "stat_cde )"
	        + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = dbConn.prepareStatement(query);
	      preparedStmt.setString (1, fund.getFundCode());
	      preparedStmt.setString (2, fund.getName());
	      preparedStmt.setString (3, fund.getCcy());
	      Util.setPreparedStatmentInt(preparedStmt, 4, fund.getRiskLvl());
	      Util.setPreparedStatmentDouble(preparedStmt, 5, fund.getDeviation());
	      Util.setPreparedStatmentDouble(preparedStmt, 6, fund.getAnnualReturn());
	      Util.setPreparedStatmentDouble(preparedStmt, 7, fund.getSharpe());
	      Util.setPreparedStatmentDouble(preparedStmt, 8, fund.getNavPrice());
	      Util.setPreparedStatmentDouble(preparedStmt, 9, fund.getBidPrice());
	      Util.setPreparedStatmentDouble(preparedStmt, 10, fund.getOfferPrice());
	      Util.setPreparedStatmentDouble(preparedStmt, 11, fund.getMin52Price());
	      Util.setPreparedStatmentDouble(preparedStmt, 12, fund.getMax52Price());
	      Util.setPreparedStatmentDate(preparedStmt, 13, fund.getPriceDate());
	      Util.setPreparedStatmentDate(preparedStmt, 14, fund.getStatisticDate());
	      preparedStmt.setString(15, fund.getFundHouse());
	      preparedStmt.setString(16, fund.getStatusCode());

	      preparedStmt.execute();
		
	}
	
	private static List<String> getFundCodes () {
		
		List<String> fundCodes = new ArrayList<String>();
		
		fundCodes.add("U62719");
		fundCodes.add("U62942");
		fundCodes.add("U62720");
		fundCodes.add("U42551");
		fundCodes.add("U61689");
		fundCodes.add("U62718");
		fundCodes.add("U61667");
		fundCodes.add("U61666");
		fundCodes.add("U61714");
		fundCodes.add("U62717");
		fundCodes.add("U61705");
		fundCodes.add("U62786");
		fundCodes.add("U39849");
		fundCodes.add("U61687");
		fundCodes.add("U62674");
		fundCodes.add("U42276");
		fundCodes.add("U61703");
		fundCodes.add("U62728");
		fundCodes.add("U62577");
		fundCodes.add("U62675");
		fundCodes.add("U62756");
		fundCodes.add("U62755");
		fundCodes.add("U62754");
		fundCodes.add("U62666");
		fundCodes.add("U62723");
		fundCodes.add("U62724");
		fundCodes.add("U62753");
		fundCodes.add("U40111");
		fundCodes.add("U43170");
		fundCodes.add("U43171");
		fundCodes.add("U62406");
		fundCodes.add("U43261");
		fundCodes.add("U43262");
		fundCodes.add("U62831");
		fundCodes.add("U62697");
		fundCodes.add("U62639");
		fundCodes.add("U62743");
		fundCodes.add("U43172");
		fundCodes.add("U43173");
		fundCodes.add("U62820");
		fundCodes.add("U62448");
		fundCodes.add("U62459");
		fundCodes.add("U62447");
		fundCodes.add("U62703");
		fundCodes.add("U39895");
		fundCodes.add("U43118");
		fundCodes.add("U43197");
		fundCodes.add("U43198");
		fundCodes.add("U62548");
		fundCodes.add("U62381");
		fundCodes.add("U62547");
		fundCodes.add("U62549");
		fundCodes.add("U61010");
		fundCodes.add("U62468");
		fundCodes.add("U43160");
		fundCodes.add("U62543");
		fundCodes.add("U62467");
		fundCodes.add("U62527");
		fundCodes.add("U43095");
		fundCodes.add("U43098");
		fundCodes.add("U62819");
		fundCodes.add("U61123");
		fundCodes.add("U61124");
		fundCodes.add("U61122");
		fundCodes.add("U62775");
		fundCodes.add("U62797");
		fundCodes.add("U62796");
		fundCodes.add("U62710");
		fundCodes.add("U62588");
		fundCodes.add("U62658");
		fundCodes.add("U62558");
		fundCodes.add("U62479");
		fundCodes.add("U62399");
		fundCodes.add("U62478");
		fundCodes.add("U62533");
		fundCodes.add("U62346");
		fundCodes.add("U62529");
		fundCodes.add("U62407");
		fundCodes.add("U62744");
		fundCodes.add("U61141");
		fundCodes.add("U62776");
		fundCodes.add("U62640");
		fundCodes.add("U62762");
		fundCodes.add("U61011");
		fundCodes.add("U43161");
		fundCodes.add("U62761");
		fundCodes.add("U62681");
		fundCodes.add("U62777");
		fundCodes.add("U62626");
		fundCodes.add("U62380");
		fundCodes.add("U43430");
		fundCodes.add("U43434");
		fundCodes.add("U62400");
		fundCodes.add("U62524");
		fundCodes.add("U62704");
		fundCodes.add("U62701");
		fundCodes.add("U62657");
		fundCodes.add("U62656");
		fundCodes.add("U62740");
		fundCodes.add("U62494");
		fundCodes.add("U62525");
		fundCodes.add("U61120");
		fundCodes.add("U61121");
		fundCodes.add("U61119");
		fundCodes.add("U62630");
		fundCodes.add("U62636");
		fundCodes.add("U62827");
		fundCodes.add("U62726");
		fundCodes.add("U61529");
		fundCodes.add("U62713");
		fundCodes.add("U62727");
		fundCodes.add("U62540");
		fundCodes.add("U62699");
		fundCodes.add("U62534");
		fundCodes.add("U62491");
		fundCodes.add("U62702");
		fundCodes.add("U62530");
		fundCodes.add("U62499");
		fundCodes.add("U62528");
		fundCodes.add("U62519");
		fundCodes.add("U62708");
		fundCodes.add("U62785");
		fundCodes.add("U62520");
		fundCodes.add("U62546");
		fundCodes.add("U62498");
		fundCodes.add("U62526");
		fundCodes.add("U62725");
		fundCodes.add("U62627");
		fundCodes.add("U62828");
		fundCodes.add("U62774");
		fundCodes.add("U62501");
		fundCodes.add("U62397");
		fundCodes.add("U62398");
		fundCodes.add("U62773");
		fundCodes.add("U61664");
		fundCodes.add("U62742");
		fundCodes.add("U61662");
		fundCodes.add("U61752");
		fundCodes.add("U62700");
		fundCodes.add("U62712");
		fundCodes.add("U62651");
		fundCodes.add("U39948");
		fundCodes.add("U62660");
		fundCodes.add("U43199");
		fundCodes.add("U43200");
		fundCodes.add("U62659");
		fundCodes.add("U62517");
		fundCodes.add("U61663");
		fundCodes.add("U61712");
		fundCodes.add("U62770");
		fundCodes.add("U62492");
		fundCodes.add("U62389");
		fundCodes.add("U62711");
		fundCodes.add("U62156");
		fundCodes.add("U62619");
		fundCodes.add("U62618");
		fundCodes.add("U62502");
		fundCodes.add("U61565");
		fundCodes.add("U61566");
		fundCodes.add("U62531");
		fundCodes.add("U62709");
		fundCodes.add("U62505");
		fundCodes.add("U61111");
		fundCodes.add("U61112");
		fundCodes.add("U61110");
		fundCodes.add("U62662");
		fundCodes.add("U62684");
		fundCodes.add("U62661");
		fundCodes.add("U62567");
		fundCodes.add("U62772");
		fundCodes.add("U62722");
		fundCodes.add("U62830");
		fundCodes.add("U62371");
		fundCodes.add("U62545");
		fundCodes.add("U62372");
		fundCodes.add("U62388");
		fundCodes.add("U62568");
		fundCodes.add("U62613");
		fundCodes.add("U62155");
		fundCodes.add("U62614");
		fundCodes.add("U62390");
		fundCodes.add("U62157");
		fundCodes.add("U62598");
		fundCodes.add("U62599");
		fundCodes.add("U62763");
		fundCodes.add("U62590");
		fundCodes.add("U62591");
		fundCodes.add("U62550");
		fundCodes.add("U62551");
		fundCodes.add("U62392");
		fundCodes.add("U62159");
		fundCodes.add("U62608");
		fundCodes.add("U62609");
		fundCodes.add("U62557");
		fundCodes.add("U62751");
		fundCodes.add("U62620");
		fundCodes.add("U61759");
		fundCodes.add("U62532");
		fundCodes.add("U62391");
		fundCodes.add("U42792");
		fundCodes.add("U62664");
		fundCodes.add("U62158");
		fundCodes.add("U62433");
		fundCodes.add("U62554");
		fundCodes.add("U62500");
		fundCodes.add("U62795");
		fundCodes.add("U62603");
		fundCodes.add("U62561");
		fundCodes.add("U62604");
		fundCodes.add("U62562");
		fundCodes.add("U62794");
		fundCodes.add("U62570");
		fundCodes.add("U62506");
		fundCodes.add("U62829");
		fundCodes.add("U62512");
		fundCodes.add("U62497");
		fundCodes.add("U43105");
		fundCodes.add("U62771");
		fundCodes.add("U62615");
		fundCodes.add("U62544");
		fundCodes.add("U62748");
		fundCodes.add("U62457");
		fundCodes.add("U43099");
		fundCodes.add("U43096");
		fundCodes.add("U62458");
		fundCodes.add("U62539");
		fundCodes.add("U62579");
		fundCodes.add("U62600");
		fundCodes.add("U62741");
		fundCodes.add("U61117");
		fundCodes.add("U61118");
		fundCodes.add("U61116");
		fundCodes.add("U61765");
		fundCodes.add("U61720");
		fundCodes.add("U61722");
		fundCodes.add("U62682");
		fundCodes.add("U62808");
		fundCodes.add("U62509");
		fundCodes.add("U43049");
		fundCodes.add("U43106");
		fundCodes.add("U61763");
		fundCodes.add("U62510");
		fundCodes.add("U62559");
		fundCodes.add("U62752");
		fundCodes.add("U62707");
		fundCodes.add("U62749");
		fundCodes.add("U62585");
		fundCodes.add("U62610");
		fundCodes.add("U42723");
		fundCodes.add("U61760");
		fundCodes.add("U62503");
		fundCodes.add("U62663");
		fundCodes.add("U62622");
		fundCodes.add("U43097");
		fundCodes.add("U43100");
		fundCodes.add("U62582");
		fundCodes.add("U62518");
		fundCodes.add("U62605");
		fundCodes.add("U62553");
		fundCodes.add("U62617");
		fundCodes.add("U40104");
		fundCodes.add("U62556");
		fundCodes.add("U62228");
		fundCodes.add("U62229");
		fundCodes.add("U62807");
		fundCodes.add("U62227");
		fundCodes.add("U62806");
		fundCodes.add("U43238");
		fundCodes.add("U43050");
		fundCodes.add("U62581");
		fundCodes.add("U62750");
		fundCodes.add("U62602");
		fundCodes.add("U61731");
		fundCodes.add("U62342");
		fundCodes.add("U62413");
		fundCodes.add("U62414");
		fundCodes.add("U62856");
		fundCodes.add("U62855");
		fundCodes.add("U62343");
		fundCodes.add("U62344");
		fundCodes.add("U62587");
		fundCodes.add("U62810");
		fundCodes.add("U62612");
		fundCodes.add("U42313");
		fundCodes.add("U62569");
		fundCodes.add("U62683");
		fundCodes.add("U62721");
		fundCodes.add("U62621");
		fundCodes.add("U62584");
		fundCodes.add("U62644");
		fundCodes.add("U62607");
		fundCodes.add("U62859");
		fundCodes.add("U43415");
		fundCodes.add("U43416");
		fundCodes.add("U62575");
		fundCodes.add("U62616");
		fundCodes.add("U62580");
		fundCodes.add("U62646");
		fundCodes.add("U62764");
		fundCodes.add("U62647");
		fundCodes.add("U62765");
		fundCodes.add("U62857");
		fundCodes.add("U62601");
		fundCodes.add("U62586");
		fundCodes.add("U62858");
		fundCodes.add("U62611");
		fundCodes.add("U62560");
		fundCodes.add("U62757");
		fundCodes.add("U40102");
		fundCodes.add("U62672");
		fundCodes.add("U62583");
		fundCodes.add("U62606");
		fundCodes.add("U62645");
		fundCodes.add("U62565");
		fundCodes.add("U62566");
		fundCodes.add("U62555");
		fundCodes.add("U62383");
		fundCodes.add("U61788");
		fundCodes.add("U61749");
		fundCodes.add("U61735");
		fundCodes.add("U62809");
		fundCodes.add("U62648");
		fundCodes.add("U62649");
		fundCodes.add("U62650");
		fundCodes.add("U62563");
		fundCodes.add("U62760");
		fundCodes.add("U62759");
		fundCodes.add("U62430");
		fundCodes.add("U62803");
		fundCodes.add("U62839");
		fundCodes.add("U62838");
		fundCodes.add("U61621");
		fundCodes.add("U62466");
		fundCodes.add("U62226");
		fundCodes.add("U62805");
		fundCodes.add("U62802");
		fundCodes.add("U62804");
		fundCodes.add("U62469");
		fundCodes.add("U62304");
		fundCodes.add("U62840");
		fundCodes.add("U62799");
		fundCodes.add("U40107");
		fundCodes.add("U62564");
		fundCodes.add("U62791");
		fundCodes.add("U62793");
		fundCodes.add("U62801");
		fundCodes.add("U62842");
		fundCodes.add("U43188");
		fundCodes.add("U62787");
		fundCodes.add("U62792");
		fundCodes.add("U62800");
		fundCodes.add("U62788");
		fundCodes.add("U62841");
		fundCodes.add("U40100");
		fundCodes.add("U62790");
		fundCodes.add("U62789");
		fundCodes.add("U43189");
		fundCodes.add("U61031");
		fundCodes.add("U36209");
		fundCodes.add("U62882");
		fundCodes.add("U62885");
		fundCodes.add("U62901");
		fundCodes.add("U62737");
		fundCodes.add("U61774");
		fundCodes.add("U62912");
		fundCodes.add("U61561");
		fundCodes.add("U61098");
		fundCodes.add("U62767");
		fundCodes.add("U62693");
		fundCodes.add("U62766");
		fundCodes.add("U61709");
		fundCodes.add("U61553");
		fundCodes.add("U61674");
		fundCodes.add("U62239");
		fundCodes.add("U61771");
		fundCodes.add("U61746");
		fundCodes.add("U62679");
		fundCodes.add("U62668");
		fundCodes.add("U62669");
		fundCodes.add("U61710");
		fundCodes.add("U62671");
		fundCodes.add("U62667");
		fundCodes.add("U62944");
		fundCodes.add("U62943");
		fundCodes.add("U62909");
		fundCodes.add("U62945");
		fundCodes.add("U61074");
		fundCodes.add("U62949");
		fundCodes.add("U62950");
		fundCodes.add("U62908");
		fundCodes.add("U62515");
		fundCodes.add("U62516");
		fundCodes.add("U62907");
		fundCodes.add("U62946");
		fundCodes.add("U61083");
		fundCodes.add("U62680");
		fundCodes.add("U62409");
		fundCodes.add("U62670");
		fundCodes.add("U39847");
		fundCodes.add("U62911");
		fundCodes.add("U62948");
		fundCodes.add("U62870");
		fundCodes.add("U62869");
		fundCodes.add("U61778");
		fundCodes.add("U62927");
		fundCodes.add("U62576");
		fundCodes.add("U62578");
		fundCodes.add("U62638");
		fundCodes.add("U43195");
		fundCodes.add("U62968");
		fundCodes.add("U42401");
		fundCodes.add("U62958");
		fundCodes.add("U62947");
		fundCodes.add("U61648");
		fundCodes.add("U62924");
		fundCodes.add("U61793");
		fundCodes.add("U61795");
		fundCodes.add("U62923");
		fundCodes.add("U61792");
		fundCodes.add("U62962");
		fundCodes.add("U62573");
		fundCodes.add("U62900");
		fundCodes.add("U62571");
		fundCodes.add("U62833");
		fundCodes.add("U62832");
		fundCodes.add("U62925");
		fundCodes.add("U62535");
		fundCodes.add("U62845");
		fundCodes.add("U62536");
		fundCodes.add("U62629");
		fundCodes.add("U62844");
		fundCodes.add("U62836");
		fundCodes.add("U62634");
		fundCodes.add("U62967");
		fundCodes.add("U62637");
		fundCodes.add("U62847");
		fundCodes.add("U62484");
		fundCodes.add("U62818");
		fundCodes.add("U62379");
		fundCodes.add("U62965");
		fundCodes.add("U62592");
		fundCodes.add("U62593");
		fundCodes.add("U62817");
		fundCodes.add("U62269");
		fundCodes.add("U62879");
		fundCodes.add("U62635");
		fundCodes.add("U62633");
		fundCodes.add("U62875");
		fundCodes.add("U62956");
		fundCodes.add("U62886");
		fundCodes.add("U62957");
		fundCodes.add("U62835");
		fundCodes.add("U62963");
		fundCodes.add("U62849");
		fundCodes.add("U62966");
		fundCodes.add("U62876");
		fundCodes.add("U62203");
		fundCodes.add("U62204");
		fundCodes.add("U62887");
		fundCodes.add("U43241");
		fundCodes.add("U62826");
		fundCodes.add("U62926");
		fundCodes.add("U62632");
		fundCodes.add("U62594");
		fundCodes.add("U62769");
		fundCodes.add("U62462");
		fundCodes.add("U62387");
		fundCodes.add("U62871");
		fundCodes.add("U62597");
		fundCodes.add("U62631");
		fundCodes.add("U62837");
		fundCodes.add("U62780");
		fundCodes.add("U61081");
		fundCodes.add("U61072");
		fundCodes.add("U61073");
		fundCodes.add("U62951");
		fundCodes.add("U62781");
		fundCodes.add("U62825");
		fundCodes.add("U61673");
		fundCodes.add("U62595");
		fundCodes.add("U62964");
		fundCodes.add("U62350");
		fundCodes.add("U62878");
		fundCodes.add("U62848");
		fundCodes.add("U42568");
		fundCodes.add("U43312");
		fundCodes.add("U43313");
		fundCodes.add("U62866");
		fundCodes.add("U62628");
		fundCodes.add("U62778");
		fundCodes.add("U62813");
		fundCodes.add("U62812");
		fundCodes.add("U62730");
		fundCodes.add("U62889");
		fundCodes.add("U62890");
		fundCodes.add("U62816");
		fundCodes.add("U62928");
		fundCodes.add("U43051");
		fundCodes.add("U62678");
		fundCodes.add("U62867");
		fundCodes.add("U62255");
		fundCodes.add("U62677");
		fundCodes.add("U62834");
		fundCodes.add("U62815");
		fundCodes.add("U62891");
		fundCodes.add("U62464");
		fundCodes.add("U40088");
		fundCodes.add("U62895");
		fundCodes.add("U62360");
		fundCodes.add("U62537");
		fundCodes.add("U61767");
		fundCodes.add("U62846");
		fundCodes.add("U61769");
		fundCodes.add("U62872");
		fundCodes.add("U62873");
		fundCodes.add("U62779");
		fundCodes.add("U62877");
		fundCodes.add("U62476");
		fundCodes.add("U62475");
		fundCodes.add("U62811");
		fundCodes.add("U62892");
		fundCodes.add("U61554");
		fundCodes.add("U62768");
		fundCodes.add("U62902");
		fundCodes.add("U62653");
		fundCodes.add("U62432");
		fundCodes.add("U62652");
		fundCodes.add("U62896");
		fundCodes.add("U62596");
		fundCodes.add("U62824");
		fundCodes.add("U62874");
		fundCodes.add("U61725");
		fundCodes.add("U61770");
		fundCodes.add("U61768");
		fundCodes.add("U62814");
		fundCodes.add("U61128");
		fundCodes.add("U61129");
		fundCodes.add("U61625");
		fundCodes.add("U61624");
		fundCodes.add("U62411");
		fundCodes.add("U62253");
		fundCodes.add("U62254");
		fundCodes.add("U62247");
		fundCodes.add("U62894");
		fundCodes.add("U62507");
		fundCodes.add("U62410");
		fundCodes.add("U62248");
		fundCodes.add("U62249");
		fundCodes.add("U62921");
		fundCodes.add("U61660");
		fundCodes.add("U62916");
		fundCodes.add("U62915");
		fundCodes.add("U43242");
		fundCodes.add("U62893");
		fundCodes.add("U62538");
		fundCodes.add("U62932");
		fundCodes.add("U62463");
		fundCodes.add("U62402");
		fundCodes.add("U62919");
		fundCodes.add("U62431");
		fundCodes.add("U62918");
		fundCodes.add("U62143");
		fundCodes.add("U62460");
		fundCodes.add("U62694");
		fundCodes.add("U62542");
		fundCodes.add("U62485");
		fundCodes.add("U43442");
		fundCodes.add("U62654");
		fundCodes.add("U62884");
		fundCodes.add("U61082");
		fundCodes.add("U62673");
		fundCodes.add("U61044");
		fundCodes.add("U62465");
		fundCodes.add("U62412");
		fundCodes.add("U62508");
		fundCodes.add("U62917");
		fundCodes.add("U40093");
		fundCodes.add("U62696");
		fundCodes.add("U61144");
		fundCodes.add("U62695");
		fundCodes.add("U61753");
		fundCodes.add("U62455");
		fundCodes.add("U62456");
		fundCodes.add("U62920");
		fundCodes.add("U61777");
		fundCodes.add("U43182");
		fundCodes.add("U62922");
		fundCodes.add("U62300");
		fundCodes.add("U62299");
		fundCodes.add("U62301");
		fundCodes.add("U43193");
		fundCodes.add("U43186");
		fundCodes.add("U62257");
		fundCodes.add("U62258");
		fundCodes.add("U62259");
		fundCodes.add("U62641");
		fundCodes.add("U62461");
		fundCodes.add("U40095");
		fundCodes.add("U62865");
		fundCodes.add("U62408");
		fundCodes.add("U62366");
		fundCodes.add("U62643");
		fundCodes.add("U62860");
		fundCodes.add("U62861");
		fundCodes.add("U38304");
		fundCodes.add("U61080");
		fundCodes.add("U62851");
		fundCodes.add("U62864");
		fundCodes.add("U42393");
		fundCodes.add("U62642");
		fundCodes.add("U39877");
		fundCodes.add("U62863");
		fundCodes.add("U62862");
		fundCodes.add("U62443");
		fundCodes.add("U62442");
		fundCodes.add("U62676");
		fundCodes.add("U62444");
		fundCodes.add("U61744");
		fundCodes.add("U22331");
		fundCodes.add("U39970");
		fundCodes.add("U62931");
		fundCodes.add("U62952");
		fundCodes.add("U62953");
		fundCodes.add("U62954");
		fundCodes.add("U62955");
		fundCodes.add("U62913");
		fundCodes.add("U62914");
		fundCodes.add("U62868");
		fundCodes.add("U62903");
		fundCodes.add("U62904");
		fundCodes.add("U62905");
		fundCodes.add("U62906");
		fundCodes.add("U62899");
		fundCodes.add("U62910");
		fundCodes.add("U62888");
		fundCodes.add("U62883");
		fundCodes.add("U62930");
		fundCodes.add("U61706");
		fundCodes.add("U90320");
		fundCodes.add("U62758");
		fundCodes.add("U62686");
		fundCodes.add("U62685");
		fundCodes.add("U62687");
		fundCodes.add("U62688");
		fundCodes.add("U62689");
		fundCodes.add("U62690");
		fundCodes.add("U62224");
		fundCodes.add("U43104");
		fundCodes.add("U43196");
		fundCodes.add("U62692");
		fundCodes.add("U40124");
		fundCodes.add("U61142");
		fundCodes.add("U61140");
		fundCodes.add("U62691");
		fundCodes.add("U61737");
		fundCodes.add("U42611");
		fundCodes.add("U61751");
		fundCodes.add("U61750");
		fundCodes.add("U90342");
		fundCodes.add("U42555");
		fundCodes.add("U43048");
		fundCodes.add("U43379");
		fundCodes.add("U43390");
		fundCodes.add("U61088");
		fundCodes.add("U90319");
		fundCodes.add("U90334");
		fundCodes.add("U90324");
		fundCodes.add("U32558");
		fundCodes.add("U40319");
		fundCodes.add("U61748");
		fundCodes.add("U61733");
		fundCodes.add("U40317");
		fundCodes.add("U43506");
		fundCodes.add("U43507");
		fundCodes.add("U43508");
		fundCodes.add("U40098");
		fundCodes.add("U61057");
		fundCodes.add("U90341");
		fundCodes.add("U61062");
		fundCodes.add("U61675");
		fundCodes.add("U61683");
		fundCodes.add("U61066");
		fundCodes.add("U61775");
		fundCodes.add("U61776");
		fundCodes.add("U61685");
		fundCodes.add("U61036");
		fundCodes.add("U61068");
		fundCodes.add("U62142");
		fundCodes.add("U61099");
		fundCodes.add("U61064");
		fundCodes.add("U42558");
		fundCodes.add("U62251");
		fundCodes.add("U61060");
		fundCodes.add("U36031");
		fundCodes.add("U61633");
		fundCodes.add("U61634");
		fundCodes.add("U42726");
		fundCodes.add("U42557");
		fundCodes.add("U61679");
		fundCodes.add("U61713");
		fundCodes.add("U62270");
		fundCodes.add("U61024");
		fundCodes.add("U61787");
		fundCodes.add("U62205");
		fundCodes.add("U62211");
		fundCodes.add("U61090");
		fundCodes.add("U61773");
		fundCodes.add("U62292");
		fundCodes.add("U61772");
		fundCodes.add("U42017");
		fundCodes.add("U90328");
		fundCodes.add("U61042");
		fundCodes.add("U62206");
		fundCodes.add("U61699");
		fundCodes.add("U61035");
		fundCodes.add("U42762");
		fundCodes.add("U85712");
		fundCodes.add("U39963");
		fundCodes.add("U42763");
		fundCodes.add("U62340");
		fundCodes.add("U62341");
		fundCodes.add("U62339");
		fundCodes.add("U62213");
		fundCodes.add("U61701");
		fundCodes.add("U62736");
		fundCodes.add("U62221");
		fundCodes.add("U62938");
		fundCodes.add("U62623");
		fundCodes.add("U61086");
		fundCodes.add("U61564");
		fundCodes.add("U62490");
		fundCodes.add("U61091");
		fundCodes.add("U62933");
		fundCodes.add("U62574");
		fundCodes.add("U62934");
		fundCodes.add("U61029");
		fundCodes.add("U62935");
		fundCodes.add("U62898");
		fundCodes.add("U61109");
		fundCodes.add("U62939");
		fundCodes.add("U62470");
		fundCodes.add("U62471");
		fundCodes.add("U62141");
		fundCodes.add("U62936");
		fundCodes.add("U62732");
		fundCodes.add("U62401");
		fundCodes.add("U62375");
		fundCodes.add("U62376");
		fundCodes.add("U61784");
		fundCodes.add("U62303");
		fundCodes.add("U62207");
		fundCodes.add("U61557");
		fundCodes.add("U61558");
		fundCodes.add("U62489");
		fundCodes.add("U62937");
		fundCodes.add("U43047");
		fundCodes.add("U61734");
		fundCodes.add("U62403");
		fundCodes.add("U62405");
		fundCodes.add("U61052");
		fundCodes.add("U42305");
		fundCodes.add("U62252");
		fundCodes.add("U61131");
		fundCodes.add("U61133");
		fundCodes.add("U62486");
		fundCodes.add("U43237");
		fundCodes.add("U43243");
		fundCodes.add("U62345");
		fundCodes.add("U61152");
		fundCodes.add("U61537");
		fundCodes.add("U90338");
		fundCodes.add("U62472");
		fundCodes.add("U62474");
		fundCodes.add("U62404");
		fundCodes.add("U61693");
		fundCodes.add("U61619");
		fundCodes.add("U61618");
		fundCodes.add("U61727");
		fundCodes.add("U85126");
		fundCodes.add("U62348");
		fundCodes.add("U62384");
		fundCodes.add("U62445");
		fundCodes.add("U62446");
		fundCodes.add("U61137");
		fundCodes.add("U61586");
		fundCodes.add("U61587");
		fundCodes.add("U61193");
		fundCodes.add("U61610");
		fundCodes.add("U61138");
		fundCodes.add("U61139");
		fundCodes.add("U42553");
		fundCodes.add("U61191");
		fundCodes.add("U61606");
		fundCodes.add("U62487");
		fundCodes.add("U61056");
		fundCodes.add("U62267");
		fundCodes.add("U61582");
		fundCodes.add("U61583");
		fundCodes.add("U43259");
		fundCodes.add("U61135");
		fundCodes.add("U61136");
		fundCodes.add("U61134");
		fundCodes.add("U90317");
		fundCodes.add("U62374");
		fundCodes.add("U90326");
		fundCodes.add("U90340");
		fundCodes.add("U62373");
		fundCodes.add("U90325");
		fundCodes.add("U43515");
		fundCodes.add("U90343");
		fundCodes.add("U61127");
		fundCodes.add("U61037");
		fundCodes.add("U61125");
		fundCodes.add("U61033");
		fundCodes.add("U42721");
		fundCodes.add("U62504");
		fundCodes.add("U62378");
		fundCodes.add("U61670");
		fundCodes.add("U62473");
		fundCodes.add("U61093");
		fundCodes.add("U61800");
		fundCodes.add("U61801");
		fundCodes.add("U61159");
		fundCodes.add("U61799");
		fundCodes.add("U23542");
		fundCodes.add("U61051");
		fundCodes.add("U62208");
		fundCodes.add("U62243");
		fundCodes.add("U42373");
		fundCodes.add("U61575");
		fundCodes.add("U61576");
		fundCodes.add("U62218");
		fundCodes.add("U43201");
		fundCodes.add("U43202");
		fundCodes.add("U42324");
		fundCodes.add("U62655");
		fundCodes.add("U61555");
		fundCodes.add("U61556");
		fundCodes.add("U61070");
		fundCodes.add("U61568");
		fundCodes.add("U90329");
		fundCodes.add("U61048");
		fundCodes.add("U62154");
		fundCodes.add("U61721");
		fundCodes.add("U62291");
		fundCodes.add("U62385");
		fundCodes.add("U61594");
		fundCodes.add("U61595");
		fundCodes.add("U61596");
		fundCodes.add("U62246");
		fundCodes.add("U62624");
		fundCodes.add("U61642");
		fundCodes.add("U61018");
		fundCodes.add("U62625");
		fundCodes.add("U62434");
		fundCodes.add("U42835");
		fundCodes.add("U62230");
		fundCodes.add("U62365");
		fundCodes.add("U62734");
		fundCodes.add("U62294");
		fundCodes.add("U90332");
		fundCodes.add("U62488");
		fundCodes.add("U61672");
		fundCodes.add("U61087");
		fundCodes.add("U61617");
		fundCodes.add("U61779");
		fundCodes.add("U90318");
		fundCodes.add("U62496");
		fundCodes.add("U43169");
		fundCodes.add("U62216");
		fundCodes.add("U62215");
		fundCodes.add("U61782");
		fundCodes.add("U42319");
		fundCodes.add("U61041");
		fundCodes.add("U61188");
		fundCodes.add("U61615");
		fundCodes.add("U61611");
		fundCodes.add("U61186");
		fundCodes.add("U61149");
		fundCodes.add("U61012");
		fundCodes.add("U61130");
		fundCodes.add("U62731");
		fundCodes.add("U61708");
		fundCodes.add("U62233");
		fundCodes.add("U62235");
		fundCodes.add("U90335");
		fundCodes.add("U61096");
		fundCodes.add("U61077");
		fundCodes.add("U62436");
		fundCodes.add("U61522");
		fundCodes.add("U62493");
		fundCodes.add("U62437");
		fundCodes.add("U43166");
		fundCodes.add("U62212");
		fundCodes.add("U62850");
		fundCodes.add("U61726");
		fundCodes.add("U61644");
		fundCodes.add("U61790");
		fundCodes.add("U61559");
		fundCodes.add("U90336");
		fundCodes.add("U62739");
		fundCodes.add("U42312");
		fundCodes.add("U61196");
		fundCodes.add("U43162");
		fundCodes.add("U62495");
		fundCodes.add("U90322");
		fundCodes.add("U61195");
		fundCodes.add("U62220");
		fundCodes.add("U62438");
		fundCodes.add("U62396");
		fundCodes.add("U90327");
		fundCodes.add("U62395");
		fundCodes.add("U61049");
		fundCodes.add("U61584");
		fundCodes.add("U61585");
		fundCodes.add("U61198");
		fundCodes.add("U62541");
		fundCodes.add("U61745");
		fundCodes.add("U24894");
		fundCodes.add("U61045");
		fundCodes.add("U42561");
		fundCodes.add("U62201");
		fundCodes.add("U61055");
		fundCodes.add("U61079");
		fundCodes.add("U61520");
		fundCodes.add("U62296");
		fundCodes.add("U62880");
		fundCodes.add("U62881");
		fundCodes.add("U61671");
		fundCodes.add("U61100");
		fundCodes.add("U61577");
		fundCodes.add("U62281");
		fundCodes.add("U24956");
		fundCodes.add("U61143");
		fundCodes.add("U62102");
		fundCodes.add("U61657");
		fundCodes.add("U61783");
		fundCodes.add("U61658");
		fundCodes.add("U42858");
		fundCodes.add("U61601");
		fundCodes.add("U61531");
		fundCodes.add("U62393");
		fundCodes.add("U62282");
		fundCodes.add("U62283");
		fundCodes.add("U62236");
		fundCodes.add("U38394");
		fundCodes.add("U62738");
		fundCodes.add("U43239");
		fundCodes.add("U38167");
		fundCodes.add("U61732");
		fundCodes.add("U61173");
		fundCodes.add("U61155");
		fundCodes.add("U61084");
		fundCodes.add("U43191");
		fundCodes.add("U61589");
		fundCodes.add("U61590");
		fundCodes.add("U61588");
		fundCodes.add("U61572");
		fundCodes.add("U61573");
		fundCodes.add("U61574");
		fundCodes.add("U62440");
		fundCodes.add("U74349");
		fundCodes.add("U62439");
		fundCodes.add("U61022");
		fundCodes.add("U90339");
		fundCodes.add("U62382");
		fundCodes.add("U61544");
		fundCodes.add("U62289");
		fundCodes.add("U62290");
		fundCodes.add("U62266");
		fundCodes.add("U43178");
		fundCodes.add("U62897");
		fundCodes.add("U62306");
		fundCodes.add("U62305");
		fundCodes.add("U43504");
		fundCodes.add("U43505");
		fundCodes.add("U43503");
		fundCodes.add("U61539");
		fundCodes.add("U61151");
		fundCodes.add("U62735");
		fundCodes.add("U62234");
		fundCodes.add("U62232");
		fundCodes.add("U61677");
		fundCodes.add("U61599");
		fundCodes.add("U61600");
		fundCodes.add("U43152");
		fundCodes.add("U62297");
		fundCodes.add("U61669");
		fundCodes.add("U62106");
		fundCodes.add("U61570");
		fundCodes.add("U61571");
		fundCodes.add("U61097");
		fundCodes.add("U62441");
		fundCodes.add("U61592");
		fundCodes.add("U61593");
		fundCodes.add("U61591");
		fundCodes.add("U61620");
		fundCodes.add("U61736");
		fundCodes.add("U43265");
		fundCodes.add("U43266");
		fundCodes.add("U43267");
		fundCodes.add("U62435");
		fundCodes.add("U62242");
		fundCodes.add("U62386");
		fundCodes.add("U62219");
		fundCodes.add("U62394");
		fundCodes.add("U61177");
		fundCodes.add("U90331");
		fundCodes.add("U61707");
		fundCodes.add("U61059");
		fundCodes.add("U61020");
		fundCodes.add("U61197");
		fundCodes.add("U90333");
		fundCodes.add("U43164");
		fundCodes.add("U61053");
		fundCodes.add("U43190");
		fundCodes.add("U62572");
		fundCodes.add("U61781");
		fundCodes.add("U61102");
		fundCodes.add("U61014");
		fundCodes.add("U61076");
		fundCodes.add("U62231");
		fundCodes.add("U61560");
		fundCodes.add("U90337");
		fundCodes.add("U61569");
		fundCodes.add("U62273");
		fundCodes.add("U62274");
		fundCodes.add("U62272");
		fundCodes.add("U61724");
		fundCodes.add("U61054");
		fundCodes.add("U61078");
		fundCodes.add("U62278");
		fundCodes.add("U62279");
		fundCodes.add("U61039");
		fundCodes.add("U62277");
		fundCodes.add("U61622");
		fundCodes.add("U62310");
		fundCodes.add("U62311");
		fundCodes.add("U62107");
		fundCodes.add("U61085");
		fundCodes.add("U41750");
		fundCodes.add("U61524");
		fundCodes.add("U90330");
		fundCodes.add("U61147");
		fundCodes.add("U61047");
		fundCodes.add("U42552");
		fundCodes.add("U42720");
		fundCodes.add("U43187");
		fundCodes.add("U61171");
		fundCodes.add("U62210");
		fundCodes.add("U61780");
		fundCodes.add("U42367");
		fundCodes.add("U62223");
		fundCodes.add("U62941");
		
		fundCodes.add("U62929");
		
		return fundCodes;
		
	}

}
