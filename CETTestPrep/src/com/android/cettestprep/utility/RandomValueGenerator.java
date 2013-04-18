package com.android.cettestprep.utility;


public class RandomValueGenerator {
	
	public static long generateRandomNumber(double f_Minimum, double f_Maximum){
		double l_RandomValue = f_Minimum + (Math.random() * (f_Maximum - f_Minimum));
		return Math.round(l_RandomValue);
		
	}

}
