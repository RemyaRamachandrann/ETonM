package com.android.cettestprep.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.android.cettestprep.R;
import com.android.cettestprep.constant.Constants;

public class DisplayScoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent l_Intent = getIntent();
		int l_Correct = l_Intent.getIntExtra("Right", 0);
		int l_Wrong = l_Intent.getIntExtra("Wrong", 0);
		int l_Unanswered = l_Intent.getIntExtra("Unanswered", 0);
		View l_View = getLayoutInflater().inflate(
				R.layout.activity_display_score, null);
		TextView l_Subject = ((TextView)(l_View.findViewById(R.id.label_subject)));
		l_Subject.setText(getIntent().getStringExtra(l_Subject.getText() +"Subject"));
		
		TextView l_Total = ((TextView)(l_View.findViewById(R.id.label_totalQuestions)));
		l_Total.setText(l_Total.getText() + String.valueOf(l_Correct+l_Wrong+l_Unanswered));
		
		TextView l_CorrectTxt = ((TextView)(l_View.findViewById(R.id.label_correct)));
		l_CorrectTxt.setText(l_CorrectTxt.getText() + String.valueOf(l_Correct));
		
		TextView l_WrongTxt = ((TextView)(l_View.findViewById(R.id.label_incorrect)));
		l_WrongTxt.setText(l_WrongTxt.getText() + String.valueOf(l_Wrong));
		
		TextView l_UnansweredTxt = ((TextView)(l_View.findViewById(R.id.label_unanswered)));
		l_UnansweredTxt.setText(l_UnansweredTxt.getText() + String.valueOf(l_Unanswered));
		
		int l_CurrentScore = (l_Correct * Constants.SCORE_RIGHT_ANSWER) - (l_Wrong * Constants.SCORE_WRONG_ANSWER);
		TextView l_ScoreTxt = ((TextView)(l_View.findViewById(R.id.label_currentScore)));
		l_ScoreTxt.setText(l_ScoreTxt.getText() + String.valueOf(l_CurrentScore));
		
		//TODO Find average...update in DB and display
		
		l_View.bringToFront();
		setContentView(l_View);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_score, menu);
		return true;
	}

}
