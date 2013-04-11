package com.android.cettestprep.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.android.cettestprep.R;

public class DisplayQuestionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_questions);
		TextView textView = new TextView(this);
		textView.setTextSize(12);
		textView.setText("Questions will be displayed here");

		// Set the text view as the activity layout
		setContentView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_questions, menu);
		return true;
	}


}
