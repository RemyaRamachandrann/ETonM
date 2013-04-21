package com.android.cettestprep.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.dao.UserDetailsDAO;

public class VerificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String l_Error = getIntent().getStringExtra("Error");

		if (l_Error == null || l_Error.equals("")) {
			String l_PhoneNo = getIntent().getStringExtra(Constants.INTENT_KEY_PHONE_NUMBER);
			String l_MailID = getIntent().getStringExtra(Constants.INTENT_KEY_EMAIL_ID);
			
			View l_View = getLayoutInflater().inflate(
					R.layout.activity_verification, null);
			String l_Message = Constants.MESSAGE_VERIFICATION_CONTENT + "\n"
					+ Constants.MESSAGE_MAIL_ID + " : " + l_MailID + "\n"
					+ Constants.MESSAGE_MOBILE_NUM + " : " + l_PhoneNo;
			TextView l_MessageTxt = ((TextView) l_View
					.findViewById(R.id.label_VerifyMsg));
			l_MessageTxt.setLines(10);
			l_MessageTxt.setText(l_Message);
			l_MessageTxt.bringToFront();
			setContentView(l_View);
		} else {

			View l_View = getLayoutInflater().inflate(
					R.layout.activity_verification, null);
			TextView l_MessageTxt = ((TextView) l_View
					.findViewById(R.id.label_VerifyMsg));
			l_MessageTxt.setLines(10);
			l_MessageTxt.setText(l_Error);
			l_MessageTxt.bringToFront();
			setContentView(l_View);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.verification, menu);
		return true;
	}

	public void verifyCode(View f_View) {
		String l_VerifyCode = getIntent().getStringExtra(Constants.INTENT_KEY_VERIFICATION_CODE);
		Object l_VCode = ((EditText) findViewById(R.id.edit_VCode)).getText();
		if (l_VCode != null && l_VCode.toString().length() != 0) {
			if (l_VerifyCode.equals(l_VCode.toString())) {
				UserDetailsDAO l_UserDetailsDAO = new UserDetailsDAO(this);
				int l_Result = l_UserDetailsDAO.updateConfirmation();
				if (l_Result == 1) {
					Intent l_Intent = new Intent(this,
							DisplayHomeActivity.class);
					startActivity(l_Intent);
					return;
				} 
			}

		} 
		Intent l_Intent = new Intent(this, VerificationActivity.class);
		l_Intent.putExtra(Constants.INTENT_KEY_ERROR, Constants.ERROR_MANDATORY_VERIFICATION_CODE);
		l_Intent.putExtra(Constants.INTENT_KEY_VERIFICATION_CODE
				, l_VerifyCode);
		startActivity(l_Intent);

	}

}
