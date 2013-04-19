package com.android.cettestprep.activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

			// TODO...The below code should be uncommented for real device
			/*
			 * Pattern l_EmailPattern = Patterns.EMAIL_ADDRESS; Account[]
			 * l_UserAccounts = AccountManager.get(this).getAccounts(); //
			 * Account l_account = new Account(name, type) for (Account
			 * l_Account : l_UserAccounts) { if
			 * (l_EmailPattern.matcher(l_Account.name).matches()) { if
			 * (l_Account.type.equals("com.google")) {
			 * l_SpinnerAdapter.add(l_Account.name);
			 * l_SpinnerAdapter.notifyDataSetChanged(); } } }
			 */

			l_SpinnerAdapter.add("remyaram1000@gmail.com");
			l_SpinnerAdapter.add("remya.ramchandran@gmail.com");
			l_SpinnerAdapter.notifyDataSetChanged();

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
				UserDetailsVO l_UserDetailsVO = l_UserDetails.getVerificationDetails();
				Intent l_Intent = new Intent(this, VerificationActivity.class);
				if(l_UserDetailsVO != null){
					l_Intent.putExtra("PhoneNo", l_UserDetailsVO.getMobileNo());
					l_Intent.putExtra("EmailID", l_UserDetailsVO.getEmailID());
					l_Intent.putExtra("VCode", l_UserDetailsVO.getVerificationCode());
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

		Intent l_Intent = new Intent(this, VerificationActivity.class);
		String l_UserName = ((EditText) findViewById(R.id.text_userName))
				.getText().toString();
		String l_PhoneNo = ((EditText) findViewById(R.id.text_mobileNo))
				.getText().toString();
		final Spinner l_SubjectSpinner = (Spinner) findViewById(R.id.spinnerMails);
		Object l_EmailIdSpinner = l_SubjectSpinner.getSelectedItem();
		String l_EmailId = "";
		if (l_EmailIdSpinner != null) {
			l_EmailId = l_EmailIdSpinner.toString();
		}

		if (l_UserName == null || l_UserName.equals("")) {
			l_Intent.putExtra("Error",
					"User Name is Mandatory. Please input the same");
			startActivity(l_Intent);
		}
		if (l_PhoneNo == null || l_PhoneNo.equals("")) {
			l_Intent.putExtra("Error",
					"Mobile Number is Mandatory. Please input the same");
			startActivity(l_Intent);
		}

		if (l_EmailId == null || l_EmailId.equals("")) {
			l_Intent.putExtra("Error",
					"Mobile Number is Mandatory. Please input the same");
			startActivity(l_Intent);
		}

		if (!MobileNumberValidator.isValidMobileNumber(l_PhoneNo)) {
			l_Intent.putExtra("Error",
					"Mobile Number is not valid. Please input a valid mobile number");
			startActivity(l_Intent);
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

			String l_VerificationCode = String.valueOf(RandomValueGenerator.generateRandomNumber(1000, 9999));
			Log.d("VerificationCode", l_VerificationCode);
			UserDetailsVO l_UserDetails = new UserDetailsVO("UID001",
					l_UserName, l_PhoneNo, l_EmailId, l_PhoneNo,
					l_Date,String.valueOf(System.currentTimeMillis()), l_VerificationCode, false);
			UserDetailsDAO l_UserDetailsDAO = new UserDetailsDAO(this);
			l_UserDetailsDAO.addUser(l_UserDetails);
			
			l_Intent.putExtra("PhoneNo", l_PhoneNo);
			l_Intent.putExtra("EmailID", l_EmailId);
			l_Intent.putExtra("VCode", l_VerificationCode);
			startActivity(l_Intent);
		}
	}

}
