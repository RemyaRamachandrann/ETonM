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
		boolean l_FetchByYear = getIntent().getBooleanExtra("FetchByYears", true);
		QuestionsDAO l_QuestionsDBHandler = new QuestionsDAO(this);
		SparseArray<Parcelable> l_Questions = null;
		if (!l_FetchByYear) {
			String l_SubjectName = getIntent().getStringExtra("Subject");
			String l_Category = getIntent().getStringExtra("Category");
			
			//SubjectsEnum l_Subject = SubjectsEnum.valueOf("Physics");
			l_Questions = l_QuestionsDBHandler.getAllQuestionsBySubject(l_SubjectName, l_Category);
		} else {
			String l_Year = getIntent().getStringExtra("Year");
			l_Questions = l_QuestionsDBHandler.getAllQuestionsByYear(l_Year);
		}


		Intent l_Intent = new Intent(this, DisplayQuestionsActivity.class);
		if (l_Questions == null || l_Questions.size() == 0) {
			l_Intent.putExtra("Error",
					"No questions found for the specified critieria");
		} else {
			Bundle f_Bundle = new Bundle();
			f_Bundle.putSparseParcelableArray("Questions", l_Questions);
			f_Bundle.putSerializable("CorrectSet", new HashSet<Integer>());
			f_Bundle.putSerializable("IncorrectSet", new HashSet<Integer>());
			f_Bundle.putSerializable("UnansweredSet", new HashSet<Integer>());
			l_Intent.putExtra("QuestionBundle", f_Bundle);
			l_Intent.putExtra("QuestionIndex", 0);
			l_Intent.putExtra("StartTimer", true);
		}
		startActivity(l_Intent);
	}
	
	
}
