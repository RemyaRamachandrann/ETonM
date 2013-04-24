package com.android.cettestprep.constant;

public final class Constants {
	
	/* Database Details */

	public static final String DATABASE_NAME = "EntranceTestDB";

	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_PHYSICS = "PhysicsQuestions";

	public static final String TABLE_CHEMISTRY = "ChemistryQuestions";

	public static final String TABLE_MATHS = "MathsQuestions";

	public static final String TABLE_BIOLOGY = "BiologyQuestions";

	public static final String TABLE_USER = "UserDetails";
	
	
	/* User Table Fields */

	public static final String KEY_USERID = "UserID";

	public static final String KEY_USERNAME = "UserName";
	
	public static final String KEY_PHONENO = "PhoneNo";

	public static final String KEY_EMAILID = "EmailID";
	
	public static final String KEY_PASSWORD = "Password";

	public static final String KEY_CREATIONDATE = "CreationDate";
	
	public static final String KEY_LASTLOGGED = "LastLoggedTime";

	public static final String KEY_VERIFY = "VerificationCode";

	public static final String KEY_CONFIRM = "Confirmed";
	
	
	/* Verfication message details */
	
	public static final String MESSAGE_VERIFICATION_CONTENT = "A verification code has been sent to your email id and phone. \n Please input the code and verify for completing the registration process.\r\n";
	
	public static final String MESSAGE_MOBILE_NUM = "Mobile Number : ";
	
	public static final String MESSAGE_MAIL_ID = "Email ID : ";
	
	/* Error Messages for Display */
	
	public static final String ERROR_MANDATORY_USER = "User Name is Mandatory. Please input the same.";
	
	public static final String ERROR_MANDATORY_MOBILE = "Mobile Number is Mandatory. Please input the same.";
			
	public static final String ERROR_MANDATORY_MAIL	= "Email ID is Mandatory. Please input the same.";
	
	public static final String ERROR_INVALID_MOBILE_NUMBER = "Mobile Number is not valid. Please input a valid mobile number.";
	
	public static final String ERROR_MANDATORY_VERIFICATION_CODE = "Please input the Verification code sent to your eMail and Phone";

	
	/* Button Names */
	
	public static final String BUTTON_SUBMIT ="Submit";
	
	public static final String BUTTON_NEXT ="Next";
	
	
	/* Score Values for calculation*/
	
	public static final int SCORE_RIGHT_ANSWER = 4;
	
	public static final int SCORE_WRONG_ANSWER = 1;
	
	/* Intent Keys */
	
	public static final String INTENT_KEY_ERROR = "Error";
			
	public static final String INTENT_KEY_USER_NAME = "User";
	
	public static final String INTENT_KEY_PHONE_NUMBER = "Phone";
	
	public static final String INTENT_KEY_EMAIL_ID = "Email";
	
	public static final String INTENT_KEY_EMAIL_INDEX = "MailIndex";
	
	public static final String INTENT_KEY_VERIFICATION_CODE = "VCode";
	
	public static final String INTENT_KEY_FETCH_BY_YEARS = "FetchByYears";
	
	public static final String INTENT_KEY_SUBJECT_NAME = "Subject";
	
	public static final String INTENT_KEY_CATEGORY_NAME = "Category";
	
	public static final String INTENT_KEY_YEAR_VALUE = "Year";
	
	public static final String INTENT_KEY_QUESTION_BUNDLE = "QuestionBundle";
	
	public static final String INTENT_KEY_QUESTION_INDEX = "QuestionIndex";
	
	
	/* Bundle Keys */
	
	public static final String BUNDLE_KEY_QUESTIONS = "Questions";
	
	public static final String BUNDLE_KEY_CORRECT_QUESTIONS = "CorrectSet";
	
	public static final String BUNDLE_KEY_INCORRECT_QUESTIONS = "IncorrectSet";
	
	public static final String BUNDLE_KEY_UNANSWERED_QUESTIONS = "UnansweredSet";
	
	/* Miscellaneous*/
	
	public static final String ACCOUNT_TYPE = "com.google";
	
	
}
