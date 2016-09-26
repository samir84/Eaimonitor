package com.hs.eai.monitor.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUtilsService {
	
	private static final String ACCES_WEEKLY_RETURN_WEEK_DATUM_PATTERN="dd-MM-yyyy";

	private static final Logger logger = LoggerFactory.getLogger(AppUtilsService.class);

	public int getMaxPages(int totalIntries, int maxResults) {

		if (mod(totalIntries, maxResults) < maxResults) {
			return (totalIntries / maxResults) + 1;
		} else {
			return 0;
		}

	}

	private int mod(int x, int y) {
		int result = x % y;
		if (result < 0) {
			result += y;
		}
		return result;
	}

	public String formatDate(long timeStamp, String pattern) {

		Timestamp stamp = null;
		Date date = null;
		String dateString = null;
		try {
			stamp = new Timestamp(timeStamp);
			date = new Date(stamp.getTime());
			// Format date
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			dateString = format.format(date);
		} catch (Exception e) {
			logger.error("Error when trying to format date {} ", date.toString(), " to: {}", pattern);
		}

		return dateString;
	}
	public  String toPercentage(float n){
	    return String.format("%.0f",n*100)+"%";
	}
	public  Float calculatePercentage(Integer issues , Integer totalIssues ){
		
		try {
			return  ((issues.floatValue() ) / totalIssues.floatValue()) ;
		}catch (Exception ex){
			logger.error(ex.getMessage());
			return null;
		}
	}
	public java.sql.Date getDateFromString(String date) {

		java.util.Date javaDate = null;
		java.sql.Date sqlDate = null;
		try {

			SimpleDateFormat format = new SimpleDateFormat(ACCES_WEEKLY_RETURN_WEEK_DATUM_PATTERN);
			javaDate = format.parse(date);
			sqlDate = new java.sql.Date(javaDate.getTime());
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return sqlDate;

	}
	
	public Double parseDouble(String hours){
		
		Double result = null;
		try{
			hours = hours.replace(",",".");
			result = Double.parseDouble(hours);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
