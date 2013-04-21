package com.android.cettestprep.activity;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.vo.QuestionsVO;

public class DisplayQuestionsActivity extends Activity {

	String m_CorrectAnswer = "";
	int m_QuestionIndex = 0;

	@SuppressWarnings("unchecked")
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
			
			QuestionsVO l_QuestionsVO = (QuestionsVO) l_QuestionsArr
					.get(l_QuestionsArr.keyAt(m_QuestionIndex));
			String l_QuestionStr = l_QuestionsVO.getQuestion();
			String l_OptionA = l_QuestionsVO.getOption1();
			String l_OptionB = l_QuestionsVO.getOption2();
			String l_OptionC = l_QuestionsVO.getOption3();
			String l_OptionD = l_QuestionsVO.getOption4();
			m_CorrectAnswer = l_QuestionsVO.getAnswer();
			

			View l_View = getLayoutInflater().inflate(
					R.layout.activity_display_questions, null);
			LinearLayout l_QuestionLayout = (LinearLayout) (l_View
					.findViewById(R.id.layout_questions));
			((TextView) l_QuestionLayout.findViewById(R.id.label_question2))
					.setText(l_QuestionStr);
			((RadioButton) l_QuestionLayout.findViewById(R.id.radio_option1))
					.setText(l_OptionA);
			((RadioButton) l_QuestionLayout.findViewById(R.id.radio_option2))
					.setText(l_OptionB);
			((RadioButton) l_QuestionLayout.findViewById(R.id.radio_option3))
					.setText(l_OptionC);
			((RadioButton) l_QuestionLayout.findViewById(R.id.radio_option4))
					.setText(l_OptionD);
			
			String l_SelectedAnswer = l_QuestionsVO.getResult();
			if(l_SelectedAnswer.length() != 0){
				RadioGroup l_RadioGrp = ((RadioGroup) l_QuestionLayout.findViewById(R.id.radio_answers));
				if(l_SelectedAnswer.equals(l_OptionA)){
					l_RadioGrp.check(R.id.radio_option1);
				} else if(l_SelectedAnswer.equals(l_OptionB)){
					l_RadioGrp.check(R.id.radio_option2);
				} else if(l_SelectedAnswer.equals(l_OptionC)){
					l_RadioGrp.check(R.id.radio_option3);
				} else if(l_SelectedAnswer.equals(l_OptionD)){
					l_RadioGrp.check(R.id.radio_option4);
					//((RadioButton) l_QuestionLayout.findViewById(R.id.radio_option3)).setSelected(true);
				} 
			}
				
			l_QuestionLayout.bringToFront();

			LinearLayout l_NavigationLayout = (LinearLayout) (l_View
					.findViewById(R.id.layout_navigation));
			
			int l_QuestionCount = l_QuestionsArr.size();
			if(getIntent().getBooleanExtra("displayUnanswered", false)){
				l_QuestionCount = ((HashSet<Integer>) l_Bundle
						.get("UnansweredSet")).size();
			} 
			if (m_QuestionIndex == 0) {
				((Button) l_NavigationLayout.findViewById(R.id.buttonPrev))
						.setEnabled(false);
				if (l_QuestionCount == 1) {
					formatButton(
							(Button) (l_NavigationLayout
									.findViewById(R.id.buttonNext)),
							Constants.BUTTON_SUBMIT);
				} else {
					formatButton(
							(Button) (l_NavigationLayout
									.findViewById(R.id.buttonNext)),
							Constants.BUTTON_NEXT);

				}
			} else if (m_QuestionIndex == (l_QuestionCount - 1)) {
				((Button) l_NavigationLayout.findViewById(R.id.buttonPrev))
						.setEnabled(true);
				formatButton(
						(Button) (l_NavigationLayout
								.findViewById(R.id.buttonNext)),
						Constants.BUTTON_SUBMIT);
			} else {
				((Button) l_NavigationLayout.findViewById(R.id.buttonPrev))
						.setEnabled(true);
				formatButton(
						(Button) (l_NavigationLayout
								.findViewById(R.id.buttonNext)),
						Constants.BUTTON_NEXT);

			}

			((TextView) (l_NavigationLayout.findViewById(R.id.label_questionNo)))
					.setText((m_QuestionIndex + 1) + "/" + l_QuestionCount);
			l_NavigationLayout.bringToFront();

			// l_View.bringToFront();
			setContentView(l_View);
			/*
			if (getIntent().getBooleanExtra("StartTimer", false)) {
				ExamTimer l_Timer = new ExamTimer(60000, 60, this);
				l_Timer.start();
			}*/
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_questions, menu);
		return true;
	}

	
	public void displayQuestion(View f_View) {
		updateCount();
		Intent l_Intent = new Intent(this, DisplayQuestionsActivity.class);
		l_Intent.putExtra("QuestionBundle",
				getIntent().getBundleExtra("QuestionBundle"));
		if (f_View.getId() == R.id.buttonNext) {
			l_Intent.putExtra("QuestionIndex", m_QuestionIndex + 1);
		} else {
			l_Intent.putExtra("QuestionIndex", m_QuestionIndex - 1);
		}

		startActivity(l_Intent);
	}

	@SuppressWarnings("unchecked")
	private void updateCount(){
		String l_SelectedAnswer = "";
		RadioGroup l_RadioGrp = ((RadioGroup) findViewById(R.id.radio_answers));
		int l_RadioID = l_RadioGrp.getCheckedRadioButtonId();
		if (l_RadioID == -1) {
			// no item selected
			Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
			addIndexToSet(m_QuestionIndex,
					((HashSet<Integer>) l_Bundle.get("UnansweredSet")));
		} else {
			RadioButton l_SelectedRadio = (RadioButton) (l_RadioGrp
					.findViewById(l_RadioID));
			l_SelectedAnswer = l_SelectedRadio.getText().toString();

		}

		if (l_SelectedAnswer.length() != 0) {
			Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
			SparseArray<Parcelable> l_QuestionsArr = l_Bundle
					.getSparseParcelableArray("Questions");
			QuestionsVO l_QuestionsVO = (QuestionsVO) l_QuestionsArr
					.get(l_QuestionsArr.keyAt(m_QuestionIndex));
			l_QuestionsVO.setResult(l_SelectedAnswer);
			if (m_CorrectAnswer.equals(l_SelectedAnswer)) {
				addIndexToSet(m_QuestionIndex,
						((HashSet<Integer>) l_Bundle.get("CorrectSet")));
				deleteIndexFromSet(m_QuestionIndex,
						((HashSet<Integer>) l_Bundle.get("IncorrectSet")));
			} else {
				addIndexToSet(m_QuestionIndex,
						((HashSet<Integer>) l_Bundle.get("IncorrectSet")));
				deleteIndexFromSet(m_QuestionIndex,
						((HashSet<Integer>) l_Bundle.get("CorrectSet")));

			}
			deleteIndexFromSet(m_QuestionIndex,
					((HashSet<Integer>) l_Bundle.get("UnansweredSet")));
		}
	}
	private void addIndexToSet(int f_Index, Set<Integer> f_Set) {
		f_Set.add(Integer.valueOf(f_Index));
	}

	private void deleteIndexFromSet(int f_Index, Set<Integer> f_Set) {
		Integer l_QuestionIdInteger = Integer.valueOf(f_Index);
		if (f_Set.contains(l_QuestionIdInteger)) {
			f_Set.remove(l_QuestionIdInteger);
		}
	}

	private void submitAnswers(final View f_View) {

		new AlertDialog.Builder(this)
				.setTitle("Submit")
				.setMessage(
						"Do you want to submit your answers or exit")
				.setPositiveButton("Submit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								displayResult();
							}
						})
				.setNegativeButton("Exit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								//displayUnanswered(f_View);
							}
						}).show();
	}

	private void formatButton(final View f_ButtonView, String f_BtnName) {
		Button l_Button = (Button) f_ButtonView;
		l_Button.setText(f_BtnName);
		if (f_BtnName.equals(Constants.BUTTON_SUBMIT)) {
			l_Button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View f_View) {
					updateCount();
					submitAnswers(f_ButtonView);
				}
			});
		} else {
			l_Button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View f_View) {
					displayQuestion(f_ButtonView);
				}
			});
		}

	}

	public void stopTimer() {
		onStop();
		new AlertDialog.Builder(this)
				.setTitle("Time Out")
				.setMessage(
						"Your time for the exam is over. Click OK to view the scores")
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						displayResult();
					}
				}).show();
	}

	private void displayResult() {
		Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
		@SuppressWarnings("unchecked")
		int l_CountRight = ((HashSet<Integer>) l_Bundle
				.get("CorrectSet")).size();
		@SuppressWarnings("unchecked")
		int l_CountWrong = ((HashSet<Integer>) l_Bundle
				.get("IncorrectSet")).size();
		@SuppressWarnings("unchecked")
		int l_CountUnanswered = ((HashSet<Integer>) l_Bundle
				.get("UnansweredSet")).size();
		Intent l_Intent = new Intent(this, DisplayScoreActivity.class);
		l_Intent.putExtra("Subject", "TBD");
		l_Intent.putExtra("Right", l_CountRight);
		l_Intent.putExtra("Wrong", l_CountWrong);
		l_Intent.putExtra("Unanswered", l_CountUnanswered);
		startActivity(l_Intent);
	}
	
	/*

	private void displayUnanswered(View f_View) {
		Bundle l_Bundle = getIntent().getBundleExtra("QuestionBundle");
		Intent l_Intent = new Intent(this, DisplayQuestionsActivity.class);
		l_Intent.putExtra("QuestionBundle",
				getIntent().getBundleExtra("QuestionBundle"));
		@SuppressWarnings("unchecked")
		Set<Integer> l_UnansweredSet = ((HashSet<Integer>) l_Bundle
				.get("UnansweredSet"));
		l_Intent.putExtra("displayUnanswered", true);
		l_Intent.putExtra("QuestionIndex", (Integer)(l_UnansweredSet.iterator().next()));
		
		startActivity(l_Intent);

	}*/
}
