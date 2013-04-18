package com.android.cettestprep.activity;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.SubjectsEnum;

public class TakeTestActivity extends Activity {

	private boolean m_IsFetchByYears;

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		m_IsFetchByYears = getIntent().getExtras().getBoolean("FetchByYears");
		LayoutInflater l_LayoutInflater = getLayoutInflater();
		View l_View = l_LayoutInflater.inflate(R.layout.activity_take_test,
				null);
		LinearLayout l_ListDisplayLayout = (LinearLayout) (l_View
				.findViewById(R.id.layout2));
		l_ListDisplayLayout.removeAllViewsInLayout();
		if (m_IsFetchByYears) {
			l_View.findViewById(R.id.tab_button2).setEnabled(true);
			l_View.findViewById(R.id.tab_button2).setClickable(true);
			l_View.findViewById(R.id.tab_button1).setEnabled(false);
			l_View.findViewById(R.id.tab_button1).setClickable(false);
			createYearsList(l_ListDisplayLayout);
		} else {
			l_View.findViewById(R.id.tab_button1).setEnabled(true);
			l_View.findViewById(R.id.tab_button1).setClickable(true);
			l_View.findViewById(R.id.tab_button2).setEnabled(false);
			l_View.findViewById(R.id.tab_button2).setClickable(false);
			createSubjectsList(l_ListDisplayLayout);
		}
		l_ListDisplayLayout.bringToFront();
		setContentView(l_View);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_test, f_Menu);
		return true;
	}

	public void selectByYears(View f_View) {
		m_IsFetchByYears = true;
		Intent l_Intent = new Intent(this, TakeTestActivity.class);
		l_Intent.putExtra("FetchByYears", m_IsFetchByYears);
		startActivity(l_Intent);
	}

	public void selectBySubject(View f_View) {
		m_IsFetchByYears = false;
		Intent l_Intent = new Intent(this, TakeTestActivity.class);
		l_Intent.putExtra("FetchByYears", m_IsFetchByYears);
		startActivity(l_Intent);
	}

	private void fetchQuestions(String f_Value, boolean f_IsFetchByYear) {
		if(f_IsFetchByYear){
			Intent l_Intent = new Intent(this, DisplayInstructionsActivity.class);
			l_Intent.putExtra("FetchByYears", f_IsFetchByYear);
			l_Intent.putExtra("Year", f_Value);
			startActivity(l_Intent);

		} else {
			Intent l_Intent = new Intent(this, DisplayCategoryActivity.class);
			l_Intent.putExtra("Subject", f_Value);
			startActivity(l_Intent);
		}
		
	}

	private void createYearsList(LinearLayout l_Layout) {
		int l_CurrentYear = Calendar.getInstance().get(Calendar.YEAR);

		for (int l_Year = l_CurrentYear - 1, l_Count = 0; l_Count < 10; l_Year--, l_Count++) {
			TextView l_TextView = new TextView(this);

			l_TextView.setClickable(true);
			l_TextView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View f_View) {
					String l_StrYear = ((TextView) f_View).getText().toString();
					fetchQuestions(l_StrYear, true);
				}
			});
			l_TextView.setTextSize(20);
			l_TextView.setText(String.valueOf(l_Year));
			l_TextView.setLinksClickable(true);
			ViewGroup.MarginLayoutParams l_MarginParams = new ViewGroup.MarginLayoutParams(
					300, 70);
			l_MarginParams.setMargins(l_MarginParams.leftMargin,
					l_MarginParams.topMargin, l_MarginParams.rightMargin,
					l_MarginParams.bottomMargin);
			l_TextView.setLayoutParams(l_MarginParams);
			l_TextView.setTextAppearance(this, R.style.CustomText);

			l_Layout.addView(l_TextView, l_Count);
		}

	}

	private void createSubjectsList(LinearLayout l_Layout) {
		SubjectsEnum l_Subjects[] = SubjectsEnum.values();
		for (int l_Count = 0; l_Count < l_Subjects.length; l_Count++) {
			TextView l_TextView = new TextView(this);
			l_TextView.setClickable(true);
			l_TextView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View f_View) {
					fetchQuestions(((TextView) f_View).getText().toString(),
							false);
				}
			});
			l_TextView.setTextSize(20);
			l_TextView.setText(l_Subjects[l_Count].toString());
			l_TextView.setLinksClickable(true);
			ViewGroup.MarginLayoutParams l_MarginParams = new ViewGroup.MarginLayoutParams(
					300, 70);
			l_MarginParams
					.setMargins(l_MarginParams.leftMargin,
							l_MarginParams.leftMargin, 100,
							l_MarginParams.bottomMargin);
			l_TextView.setLayoutParams(l_MarginParams);
			l_TextView.setTextAppearance(this, R.style.CustomText);
			l_Layout.addView(l_TextView, l_Count);

		}

	}
}
