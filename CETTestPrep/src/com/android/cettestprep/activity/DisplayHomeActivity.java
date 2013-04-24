package com.android.cettestprep.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;

public class DisplayHomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		setContentView(R.layout.activity_display_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_home, f_Menu);
		return true;
	}
	
	public void takeTest(View f_View){
		Intent l_Intent = new Intent(this, TakeTestActivity.class);
		l_Intent.putExtra(Constants.INTENT_KEY_FETCH_BY_YEARS, true);
		startActivity(l_Intent);
	}

}
