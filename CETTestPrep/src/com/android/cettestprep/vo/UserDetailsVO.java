package com.android.cettestprep.vo;

import java.sql.Date;
import java.sql.Time;

public class UserDetailsVO {

	private String m_UserID = "";

	private String m_UserName = "";

	private String m_MobileNo;

	private String m_EmailID = "";

	private String m_Password;

	private String m_CreationDate;

	private String m_LastLoggedTime;

	private String m_VerificationCode = "";

	private boolean m_Verified;
	
	public UserDetailsVO(){
		
	}

	public UserDetailsVO(String f_UID, String f_UserName, String f_PhoneNo,
			String f_Email, String f_Password, String f_CreationDate,
			String f_LastLogTime, String f_VerificationCode, boolean f_Verified) {
		m_UserID = f_UID;
		m_UserName = f_UserName;
		m_MobileNo = f_PhoneNo;
		m_EmailID = f_Email;
		m_Password = f_Password;
		m_CreationDate = f_CreationDate;
		m_LastLoggedTime = f_LastLogTime;
		m_VerificationCode = f_VerificationCode;
		m_Verified = f_Verified;
		

	}

	public String getUserID() {
		return m_UserID;
	}

	public void setUserID(String f_userID) {
		m_UserID = f_userID;
	}

	public String getUserName() {
		return m_UserName;
	}

	public void setUserName(String f_userName) {
		m_UserName = f_userName;
	}

	public String getMobileNo() {
		return m_MobileNo;
	}

	public void setMobileNo(String f_mobileNo) {
		m_MobileNo = f_mobileNo;
	}

	public String getEmailID() {
		return m_EmailID;
	}

	public void setEmailID(String f_emailID) {
		m_EmailID = f_emailID;
	}

	public String getPassword() {
		return m_Password;
	}

	public void setPassword(String f_password) {
		m_Password = f_password;
	}

	public String getCreationDate() {
		return m_CreationDate;
	}

	public void setCreationDate(String f_creationDate) {
		m_CreationDate = f_creationDate;
	}

	public String getLastLoggedTime() {
		return m_LastLoggedTime;
	}

	public void setLastLoggedTime(String f_lastLoggedTime) {
		m_LastLoggedTime = f_lastLoggedTime;
	}

	public String getVerificationCode() {
		return m_VerificationCode;
	}

	public void setVerificationCode(String f_verificationCode) {
		m_VerificationCode = f_verificationCode;
	}

	public boolean isVerified() {
		return m_Verified;
	}

	public void setVerified(boolean f_Verified) {
		m_Verified = f_Verified;
	}
}
