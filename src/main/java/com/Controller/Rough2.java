package com.Controller;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Rough2 {

	public static void main(String[] args) {

		// Parsing Time Period in the format HH:MM:SS
		LocalTime time1 = LocalTime.of(7,00,00);
		LocalTime time2 = LocalTime.of(10,5,00);
		

		// Calculating the difference in Hours
		long hours = ChronoUnit.HOURS.between(time1, time2);

		// Calculating the difference in Minutes
		long minutes = ChronoUnit.MINUTES.between(time1, time2) % 60;

		// Calculating the difference in Seconds
		long seconds = ChronoUnit.SECONDS.between(time1, time2) % 60;

		// Printing the difference
		System.out.println("Difference is " + hours + " hours " + minutes + " minutes " + seconds + " seconds.");
	}

}
