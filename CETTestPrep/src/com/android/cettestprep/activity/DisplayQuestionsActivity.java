package com.android.cettestprep.activity;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.vo.QuestionsVO;

public class DisplayQuestionsActivity extends Activity {

	char m_SelectedAnswer = ' ';
	char m_CorrectAnswer = ' ';
	int m_QuestionIndex = 0;
	int m_QuestionID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String l_Error = getIntent().getStringExtra("Error");
		if (l_Error != null) {
			TextView textView = new TextView(this);
			textView.setTextSize(12);
			textView.setText(l_Error);
			setContentView(textView);
		} else {
			Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
			SparseArray<Parcelable> l_QuestionsArr = l_Bundle
					.getSparseParcelableArray("Questions");
			m_QuestionIndex = getIntent().getIntExtra("QuestionIndex", 0);
			m_QuestionID = l_QuestionsArr.keyAt(m_QuestionIndex);

			QuestionsVO l_QuestionsVO = (QuestionsVO) l_QuestionsArr
					.get(m_QuestionID);
			String l_QuestionStr = l_QuestionsVO.getQuestion();
			String l_OptionA = l_QuestionsVO.getOption1();
			String l_OptionB = l_QuestionsVO.getOption2();
			String l_OptionC = l_QuestionsVO.getOption3();
			String l_OptionD = l_QuestionsVO.getOption4();
			
			View l_View = getLayoutInflater().inflate(R.layout.activity_display_questions,
					null);
			LinearLayout l_ListDisplayLayout = (LinearLayout) (l_View
					.findViewById(R.id.layout_questions));
			((TextView) l_ListDisplayLayout.findViewById(R.id.label_question2))
					.setText(l_QuestionStr);
			((RadioButton) l_ListDisplayLayout.findViewById(R.id.radio_option1)).setText(l_OptionA);
			((RadioButton) l_ListDisplayLayout.findViewById(R.id.radio_option2)).setText(l_OptionB);
			((RadioButton) l_ListDisplayLayout.findViewById(R.id.radio_option3)).setText(l_OptionC);
			((RadioButton) l_ListDisplayLayout.findViewById(R.id.radio_option4)).setText(l_OptionD);
			l_ListDisplayLayout.bringToFront();
			setContentView(l_View);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_questions, menu);
		return true;
	}

	@SuppressWarnings("unchecked")
	public void displayNextQuestion(View f_View) {
		if (m_SelectedAnswer != ' ') {
			Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
			if (m_CorrectAnswer == m_SelectedAnswer) {

				addKeyToSet(m_QuestionID,
						((HashSet<Integer>) l_Bundle.get("CorrectSet")));
				deleteKeyFromSet(m_QuestionID,
						((HashSet<Integer>) l_Bundle.get("IncorrectSet")));
			} else {
				addKeyToSet(m_QuestionID, ((HashSet<Integer>) l_Bundle.get("IncorrectSet")));
				deleteKeyFromSet(m_QuestionID,
						((HashSet<Integer>) l_Bundle.get("CorrectSet")));

			}
			deleteKeyFromSet(m_QuestionID,
					((HashSet<Integer>) l_Bundle.get("UnansweredSet")));
		} else {
			Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
			deleteKeyFromSet(m_QuestionID,((HashSet<Integer>) l_Bundle
					.get("UnansweredSet")));
		}
		int l_CurrentIndex = getIntent().getIntExtra("QuestionIndex", 0);
		Intent l_Intent = new Intent(this, DisplayQuestionsActivity.class);
		l_Intent.putExtra("QuestionBundle",
				getIntent().getBundleExtra("QuestionBundle"));
		l_Intent.putExtra("QuestionIndex", ++l_CurrentIndex);
	}

	private void addKeyToSet(int f_Key, Set<Integer> f_Set) {
		f_Set.add(Integer.valueOf(f_Key));
	}

	private void deleteKeyFromSet(int f_Key, Set<Integer> f_Set) {
		Integer l_QuestionIdInteger = Integer.valueOf(f_Key);
		if (f_Set.contains(l_QuestionIdInteger)) {
			f_Set.remove(l_QuestionIdInteger);
		}
	}
}
