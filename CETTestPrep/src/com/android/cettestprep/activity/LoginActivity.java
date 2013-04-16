package com.android.cettestprep.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.android.cettestprep.R;
import com.android.cettestprep.dao.DatabaseHandler;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		DatabaseHandler f_DBHandler = new DatabaseHandler(this);
		try {
			f_DBHandler.createDataBase();
			f_DBHandler.close();
		} catch (IOException ioe) {
			// throw new Error("Unable to create database");
		}
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, f_Menu);
		return true;
	}

	public void checkPasswd(View f_View) {
		Intent l_Intent = new Intent(this, DisplayHomeActivity.class);
		startActivity(l_Intent);
	}

}
