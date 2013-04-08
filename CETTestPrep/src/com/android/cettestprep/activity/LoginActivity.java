package com.android.cettestprep.activity;

import com.android.cettestprep.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, f_Menu);
		return true;
	}
	
	public void checkPasswd(View f_View){
		Intent l_Intent = new Intent(this, DisplayHomeActivity.class);
		startActivity(l_Intent);
	}

}
