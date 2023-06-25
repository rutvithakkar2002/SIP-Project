package com.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rough {

	public static void main(String[] args) throws ParseException {
	/*	SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM");
		String strMonth = simpleformat.format(new Date());
		System.out.println("Month in MMMM format = " + strMonth);

		Date d = new Date();
		int year = d.getYear();

		System.out.println(year);

		Calendar cal = Calendar.getInstance();
		System.out.println("Year = " + cal.get(Calendar.YEAR));*/
		
		
		
		/*String s1="java javatpoint";  
		String[] words=s1.split("\\s");//splits the string based on whitespace  
		//using java foreach loop to print elements of string array  
		System.out.println(words[1]);
		*/
		
/*		String t1 = "hii 18:00:00";
		String t2 = "hii 7:30:50";
		
		String s1 = t1;
		String s2 = t2;
		String[] words1 = s1.split("\\s");// splits the string based on whitespace
		String[] words2 = s2.split("\\s");
		// using java foreach loop to print elements of string array
		String w1 = words1[1];
		String w2 = words2[1];

		System.out.println(w1);
		System.out.println(w2);*/
		
		 // Dates to be parsed
      //  String time1 = "11:04:04";
     //   String time2 = "01:05:16";
  
        // Creating a SimpleDateFormat object
        // to parse time in the format HH:MM:SS
    //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  
        // Parsing the Time Period
      //  Date date1 = simpleDateFormat.parse(time1);
      //  Date date2 = simpleDateFormat.parse(time2);
  
        // Calculating the difference in milliseconds
     /*   long differenceInMilliSeconds
            = Math.abs(date2.getTime() - date1.getTime());
  
         Calculating the difference in Hours
        long differenceInHours
            = (differenceInMilliSeconds / (60 * 60 * 1000))
              % 24;
  
         Calculating the difference in Minutes
        long differenceInMinutes
            = (differenceInMilliSeconds / (60 * 1000)) % 60;
  
         Calculating the difference in Seconds
        long differenceInSeconds
            = (differenceInMilliSeconds / 1000) % 60;
  
        Printing the answer
        System.out.println(
            "Difference is " + differenceInHours + " hours "
            + differenceInMinutes + " minutes "
            + differenceInSeconds + " Seconds. ");*/

		 String time1 = "11:04:04";
		    String time2 = "01:05:16";
		 
		    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		    Date date1 = format.parse(time1);
		    Date date2 = format.parse(time2);
		    // Difference in Milliseconds
		    long diffInMs = date2.getTime() - date1.getTime();
		 
		    long diffSeconds = diffInMs / 1000 % 60;
		    long diffMinutes = diffInMs / (60 * 1000) % 60;
		    long diffHours = diffInMs / (60 * 60 * 1000) % 24;
		 
		    System.out.format("Difference : %d hours, %d minutes, %d seconds",
		            diffHours, diffMinutes, diffSeconds);
		
		
		
		
	}

}
