package com.android.cettestprep.activity;

import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.dao.QuestionsDAO;

public class DisplayInstructionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle f_SavedInstanceState) {
		super.onCreate(f_SavedInstanceState);
		setContentView(R.layout.activity_display_instructions);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu f_Menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_instructions, f_Menu);
		return true;
	}

	public void viewQuestions(View f_View) {
		boolean l_FetchByYear = getIntent().getBooleanExtra(Constants.INTENT_KEY_FETCH_BY_YEARS, true);
		QuestionsDAO l_QuestionsDBHandler = new QuestionsDAO(this);
		SparseArray<Parcelable> l_Questions = null;
		Intent l_Intent = new Intent(this, DisplayQuestionsActivity.class);
		if (!l_FetchByYear) {
			String l_SubjectName = getIntent().getStringExtra(Constants.INTENT_KEY_SUBJECT_NAME);
			String l_Category = getIntent().getStringExtra(Constants.INTENT_KEY_CATEGORY_NAME);
			l_Intent.putExtra(Constants.INTENT_KEY_SUBJECT_NAME, l_SubjectName);
			l_Intent.putExtra(Constants.INTENT_KEY_CATEGORY_NAME, l_Category);
			
			//SubjectsEnum l_Subject = SubjectsEnum.valueOf("Physics");
			l_Questions = l_QuestionsDBHandler.getAllQuestionsBySubject(l_SubjectName, l_Category);
		} else {
			String l_Year = getIntent().getStringExtra(Constants.INTENT_KEY_YEAR_VALUE);
			l_Intent.putExtra(Constants.INTENT_KEY_CATEGORY_NAME, l_Year);
			l_Questions = l_QuestionsDBHandler.getAllQuestionsByYear(l_Year);
		}

		if (l_Questions == null || l_Questions.size() == 0) {
			l_Intent.putExtra(Constants.INTENT_KEY_ERROR,
					"No questions found for the specified critieria");
		} else {
			Bundle f_Bundle = new Bundle();
			f_Bundle.putSparseParcelableArray(Constants.BUNDLE_KEY_QUESTIONS, l_Questions);
			f_Bundle.putSerializable(Constants.BUNDLE_KEY_CORRECT_QUESTIONS, new HashSet<Integer>());
			f_Bundle.putSerializable(Constants.BUNDLE_KEY_INCORRECT_QUESTIONS, new HashSet<Integer>());
			f_Bundle.putSerializable(Constants.BUNDLE_KEY_UNANSWERED_QUESTIONS, new HashSet<Integer>());
			l_Intent.putExtra(Constants.INTENT_KEY_QUESTION_BUNDLE, f_Bundle);
			l_Intent.putExtra(Constants.INTENT_KEY_QUESTION_INDEX, 0);
			//l_Intent.putExtra("StartTimer", true);
		}
		startActivity(l_Intent);
	}
	
	
}
