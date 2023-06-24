package com.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rough {

	public static void main(String[] args) {
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM");
		String strMonth = simpleformat.format(new Date());
		System.out.println("Month in MMMM format = " + strMonth);

		Date d = new Date();
		int year = d.getYear();

		System.out.println(year);

		Calendar cal = Calendar.getInstance();
		System.out.println("Year = " + cal.get(Calendar.YEAR));

	}

}
