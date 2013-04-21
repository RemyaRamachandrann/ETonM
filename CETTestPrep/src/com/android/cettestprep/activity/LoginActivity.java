package com.android.cettestprep.activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.dao.DatabaseHandler;
import com.android.cettestprep.dao.UserDetailsDAO;
import com.android.cettestprep.utility.MobileNumberValidator;
import com.android.cettestprep.utility.RandomValueGenerator;
import com.android.cettestprep.vo.UserDetailsVO;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		DatabaseHandler f_DBHandler = new DatabaseHandler(this);
		boolean l_DB_Exist = f_DBHandler.checkDataBase();
		if (!l_DB_Exist) {
			View l_View = getLayoutInflater().inflate(R.layout.activity_login,
					null);
			Spinner l_MailIDDropDown = ((Spinner) l_View
					.findViewById(R.id.spinnerMails));
			ArrayAdapter<String> l_SpinnerAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item,
					android.R.id.text1);
			l_SpinnerAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			l_MailIDDropDown.setAdapter(l_SpinnerAdapter);

			Pattern l_EmailPattern = Patterns.EMAIL_ADDRESS;
			Account[] l_UserAccounts = AccountManager.get(this).getAccounts(); //
			for (Account l_Account : l_UserAccounts) {
				if (l_EmailPattern.matcher(l_Account.name).matches()) {
					if (l_Account.type.equals(Constants.ACCOUNT_TYPE)) {
						l_SpinnerAdapter.add(l_Account.name);
						l_SpinnerAdapter.notifyDataSetChanged();
					}
				}
			}

			// TODO...The below code should be commented for real device
			l_SpinnerAdapter.add("remyaram1000@gmail.com");
			l_SpinnerAdapter.add("remya.ramchandran@gmail.com");
			l_SpinnerAdapter.notifyDataSetChanged();

			Intent l_Intent = getIntent();
			String l_Error = l_Intent
					.getStringExtra(Constants.INTENT_KEY_ERROR);
			if (l_Error != null && l_Error.length() > 0) {
				TextView l_MessageTxt = ((TextView) l_View
						.findViewById(R.id.textError));
				l_MessageTxt.setLines(10);
				l_MessageTxt.setText(l_Error);
				l_MessageTxt.bringToFront();
				((EditText) l_View.findViewById(R.id.text_userName)).setText(l_Intent
						.getStringExtra(Constants.INTENT_KEY_USER_NAME));
				((EditText) l_View.findViewById(R.id.text_mobileNo)).setText(l_Intent.getStringExtra(Constants.INTENT_KEY_PHONE_NUMBER));
				long l_EmailIndex = l_Intent.getLongExtra(Constants.INTENT_KEY_EMAIL_INDEX, 0);
					final Spinner l_SpinnerMailID = (Spinner) l_View.findViewById(R.id.spinnerMails);
					l_SpinnerMailID.setSelection((int)l_EmailIndex);
					
			}
			/*
			 * TelephonyManager tMgr
			 * =(TelephonyManager)this.getSystemService(Context
			 * .TELEPHONY_SERVICE); String mPhoneNumber = tMgr.getLine1Number();
			 */
			setContentView(l_View);
		} else {
			UserDetailsDAO l_UserDetails = new UserDetailsDAO(this);
			Boolean l_Confirmation = Boolean.parseBoolean(l_UserDetails
					.getOneField(Constants.KEY_CONFIRM));
			if (!l_Confirmation) {
				UserDetailsVO l_UserDetailsVO = l_UserDetails
						.getVerificationDetails();
				Intent l_Intent = new Intent(this, VerificationActivity.class);
				if (l_UserDetailsVO != null) {
					l_Intent.putExtra(Constants.INTENT_KEY_PHONE_NUMBER, l_UserDetailsVO.getMobileNo());
					l_Intent.putExtra(Constants.INTENT_KEY_EMAIL_ID, l_UserDetailsVO.getEmailID());
					l_Intent.putExtra(Constants.INTENT_KEY_VERIFICATION_CODE,
							l_UserDetailsVO.getVerificationCode());
				}
				startActivity(l_Intent);
			} else {
				Intent l_Intent = new Intent(this, DisplayHomeActivity.class);
				startActivity(l_Intent);
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, f_Menu);
		return true;
	}

	public void registerUser(View f_View) {

		String l_UserName = ((EditText) findViewById(R.id.text_userName))
				.getText().toString();
		String l_PhoneNo = ((EditText) findViewById(R.id.text_mobileNo))
				.getText().toString();
		final Spinner l_SpinnerMailID = (Spinner) findViewById(R.id.spinnerMails);

		if (isMandatoryCheckFail(l_UserName, l_PhoneNo, l_SpinnerMailID)) {
			return;
		}

		if (!MobileNumberValidator.isValidMobileNumber(l_PhoneNo)) {
			Intent l_Intent = new Intent(this, LoginActivity.class);
			l_Intent.putExtra(Constants.INTENT_KEY_ERROR,
					Constants.ERROR_INVALID_MOBILE_NUMBER);
			l_Intent.putExtra(Constants.INTENT_KEY_USER_NAME, l_UserName);
			l_Intent.putExtra(Constants.INTENT_KEY_PHONE_NUMBER, l_PhoneNo);
			l_Intent.putExtra(Constants.INTENT_KEY_EMAIL_INDEX,
					l_SpinnerMailID.getSelectedItemId());
			startActivity(l_Intent);
			return;
		} else {
			DatabaseHandler f_DBHandler = new DatabaseHandler(this);
			try {
				f_DBHandler.createDataBase();
				f_DBHandler.close();
			} catch (IOException f_Exception) {
				// throw new Error("Unable to create database");
			}
			SimpleDateFormat l_DateFormat = new SimpleDateFormat("yyyy/MM/dd");
			// get current date time with Date()
			Calendar l_Calender = Calendar.getInstance();
			String l_Date = l_DateFormat.format(l_Calender.getTime());

			String l_VerificationCode = String.valueOf(RandomValueGenerator
					.generateRandomNumber(1000, 9999));
			Log.d("VerificationCode", l_VerificationCode);
			
			 SmsManager l_SmsManager = SmsManager.getDefault();
			 l_SmsManager.sendTextMessage(l_PhoneNo, null, "Code : " +
			 l_VerificationCode, null, null);
			

			String l_EmailID = l_SpinnerMailID.getSelectedItem().toString();
			UserDetailsVO l_UserDetails = new UserDetailsVO("UID001",
					l_UserName, l_PhoneNo, l_EmailID, l_PhoneNo, l_Date,
					String.valueOf(System.currentTimeMillis()),
					l_VerificationCode, false);
			UserDetailsDAO l_UserDetailsDAO = new UserDetailsDAO(this);
			l_UserDetailsDAO.addUser(l_UserDetails);

			Intent l_Intent = new Intent(this, VerificationActivity.class);
			l_Intent.putExtra(Constants.INTENT_KEY_PHONE_NUMBER, l_PhoneNo);
			l_Intent.putExtra(Constants.INTENT_KEY_EMAIL_ID, l_EmailID);
			l_Intent.putExtra(Constants.INTENT_KEY_VERIFICATION_CODE,
					l_VerificationCode);
			startActivity(l_Intent);
		}
	}

	private boolean isMandatoryCheckFail(String f_UserName, String f_PhoneNo,
			Spinner f_SpinnerEmail) {

		String l_Error = "";
		Intent l_Intent = new Intent(this, LoginActivity.class);
		if (f_UserName == null || f_UserName.length() == 0) {
			l_Error = Constants.ERROR_MANDATORY_USER + "\n";
		}
		l_Intent.putExtra(Constants.INTENT_KEY_USER_NAME, f_UserName);

		if (f_PhoneNo == null || f_PhoneNo.equals("")) {
			l_Error = l_Error + Constants.ERROR_MANDATORY_MOBILE + "\n";

		}
		l_Intent.putExtra(Constants.INTENT_KEY_PHONE_NUMBER, f_PhoneNo);

		String l_EmailId = "";
		if (f_SpinnerEmail != null) {
			Object l_EmailIdSpinner = f_SpinnerEmail.getSelectedItem();
			if (l_EmailIdSpinner != null) {
				l_EmailId = l_EmailIdSpinner.toString();
			}
		}
		if (l_EmailId.length() == 0) {
			l_Error = l_Error + Constants.ERROR_MANDATORY_MAIL;

		} else {
			l_Intent.putExtra(Constants.INTENT_KEY_EMAIL_INDEX,
					String.valueOf(f_SpinnerEmail.getSelectedItemId()));
		}

		if (l_Error.length() != 0) {
			l_Intent.putExtra(Constants.INTENT_KEY_ERROR, l_Error);
			startActivity(l_Intent);
			return true;
		}
		return false;
	}

}
