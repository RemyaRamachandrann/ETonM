package com.android.cettestprep.activity;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.dao.DatabaseHandler;

public class TakeTestActivity extends Activity {

	private boolean m_IsFetchByYears = true;

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		findViewById(R.id.tab_button1).setEnabled(false);
		findViewById(R.id.tab_button1).setClickable(false);
		LinearLayout l_ListDisplayLayout = (LinearLayout)findViewById(R.id.layout2);
		l_ListDisplayLayout.removeAllViewsInLayout();
		if (m_IsFetchByYears) {
			createYearsList(l_ListDisplayLayout);
		} else {
			createSubjectsList(l_ListDisplayLayout);
		}
		setContentView(R.layout.activity_take_test);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_test, f_Menu);
		return true;
	}

	public void selectByYears(View f_View) {
		findViewById(R.id.tab_button1).setEnabled(true);
		findViewById(R.id.tab_button1).setClickable(true);
		findViewById(R.id.tab_button2).setEnabled(false);
		findViewById(R.id.tab_button2).setClickable(false);
		m_IsFetchByYears = true;
	}

	public void selectBySubject(View f_View) {
		findViewById(R.id.tab_button2).setEnabled(true);
		findViewById(R.id.tab_button2).setClickable(true);
		findViewById(R.id.tab_button1).setEnabled(false);
		findViewById(R.id.tab_button1).setClickable(false);
		m_IsFetchByYears = false;
	}

	private void fetchQuestions(int f_Year){
		DatabaseHandler l_DBHandler = new DatabaseHandler(this);
		l_DBHandler.getAllQuestionsByYear(f_Year);
	}
	
	private void fetchQuestions(String f_Subject){
		DatabaseHandler l_DBHandler = new DatabaseHandler(this);
		l_DBHandler.getAllQuestionsBySubject(f_Subject);
	}
	
	private void createYearsList(LinearLayout l_Layout){
		int l_CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		for (int l_Year = l_CurrentYear - 1, l_Count = 1; l_Count <=10 ; l_Year--, l_Count++) {
			TextView l_TextView = new TextView(this);
			
			l_TextView.setClickable(true);
			l_TextView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View f_View) {
					String l_StrYear = ((TextView)f_View).getText().toString();
					fetchQuestions(Integer.parseInt(l_StrYear));
				}
			});
			l_TextView.setTextSize(12);
			l_TextView.setText(String.valueOf(l_Year));
			l_Layout.addView(l_TextView, l_Count);
		}
		
	}
	
	private void createSubjectsList(LinearLayout l_Layout){
		String l_Subjects[] = {"Physics", "Chemistry", "Biology", "Mathematics"};
		
		for (int l_Count = 0; l_Count < l_Subjects.length; l_Count++) {
			TextView l_TextView = new TextView(this);
			l_TextView.setClickable(true);
			l_TextView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View f_View) {
					fetchQuestions(((TextView)f_View).getText().toString());
					
				}
			});
			l_TextView.setTextSize(12);
			l_TextView.setText(l_Subjects[l_Count]);
			l_Layout.addView(l_TextView, l_Count);
			
		}
	
	}
}
