package com.java.my_hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date convertToDate(String date) throws ParseException {
		String ptn = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(ptn);
		Date new_date;
		
		new_date = sdf.parse(date);
		
		return new_date;
	}
}
