package com.android.cettestprep.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MobileNumberValidator {
	
	PATTERN_1("[+]?[0-9]{10}"), 
	PATTERN_2("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$"), 
	PATTERN_3("[0-9]{10}"), 
	PATTERN_4("[+]?[0-9]{2}[-][0-9]{10}"), 
	PATTERN_5("[+]?[0-9]{12}");

	String m_Pattern = "";

	MobileNumberValidator(String f_Pattern) {
		this.m_Pattern = f_Pattern;
	}

	public String getPattern() {
		return m_Pattern;
	}

	public static boolean isValidMobileNumber(String f_MobNo) {
		for (MobileNumberValidator l_RegExpression : values()) {
			Pattern l_Pattern = Pattern.compile(l_RegExpression.getPattern());
			Matcher l_Matcher = l_Pattern.matcher(f_MobNo);
			if (l_Matcher.matches())
				return true;
		}
		return false;
	}
}