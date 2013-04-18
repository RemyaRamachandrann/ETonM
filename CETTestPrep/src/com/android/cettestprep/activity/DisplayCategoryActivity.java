package com.android.cettestprep.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.android.cettestprep.R;

public class DisplayCategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		setContentView(R.layout.activity_display_category);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_category, f_Menu);
		return true;
	}


	public void viewInstructions(View f_View){
		String l_SubjectName = getIntent().getStringExtra("Subject");
		String l_CategoryName = "";
		Intent l_Intent = new Intent(this, DisplayInstructionsActivity.class);
		l_Intent.putExtra("FetchByYears", false);
		l_Intent.putExtra("Subject", l_SubjectName);
		l_Intent.putExtra("Category", l_CategoryName);
		startActivity(l_Intent);
		
	}
}
