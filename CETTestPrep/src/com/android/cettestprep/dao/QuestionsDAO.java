package com.android.cettestprep.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.util.SparseArray;

import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.vo.QuestionsVO;

public class QuestionsDAO extends SQLiteOpenHelper {

	public QuestionsDAO(Context f_context) {
		super(f_context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase f_DB) {
		// No Need to create as the DB will be copied initially and not created

	}

	@Override
	public void onUpgrade(SQLiteDatabase f_DB, int f_OldVersion,
			int f_NewVersion) {
		f_DB.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PHYSICS);
	}

	public SparseArray<Parcelable> getAllQuestionsByYear(String f_Year) {

		String l_Query = "SELECT  * FROM " + Constants.TABLE_PHYSICS + " WHERE YEAR = '"
				+ f_Year + "'";

		// TODO This has to be done for all the subjects
		return getQuestions(l_Query);
	}

	public SparseArray<Parcelable> getAllQuestionsBySubject(
			String f_Subject, String f_Category) {

		String l_TableName = "";
		if(f_Subject == null || f_Subject.length() == 0){
			return null;
			
		} else if(f_Subject.equals("Physics")){
			l_TableName = Constants.TABLE_PHYSICS;
		} else if(f_Subject.equals("Chemistry")){
			l_TableName = Constants.TABLE_CHEMISTRY;
		} else if(f_Subject.equals("Biology")){
			l_TableName = Constants.TABLE_BIOLOGY;
		} else if(f_Subject.equals("Mathematics")){
			l_TableName = Constants.TABLE_MATHS;
		}
		/*
		switch (f_Subject) {

		case PHYSICS:
			l_TableName = Constants.TABLE_PHYSICS;
			break;

		case CHEMISTRY:
			l_TableName = Constants.TABLE_CHEMISTRY;
			break;

		case MATHS:
			l_TableName = Constants.TABLE_MATHS;
			break;

		case BIOLOGY:
			l_TableName = Constants.TABLE_BIOLOGY;
			break;

		default:
			return null;
		}*/
		
		//TODO Category shud also be a part of the criteria to fetch data
		String l_Query = "SELECT  * FROM " + l_TableName;

		return getQuestions(l_Query);
	}

	public SparseArray<Parcelable> getQuestions(String f_Query) {
		SparseArray<Parcelable> l_QuestionsArray = new SparseArray<Parcelable>();

		// Map<Integer,QuestionsVO> l_QuestionsMap = new HashMap<Integer,
		// QuestionsVO>();
		SQLiteDatabase l_Database = this.getWritableDatabase();
		Cursor l_Cursor = l_Database.rawQuery(f_Query, null);

		// looping through all rows and adding to list
		if (l_Cursor.moveToFirst()) {
			do {
				QuestionsVO l_QuestionVO = new QuestionsVO(
						Integer.parseInt(l_Cursor.getString(0)),
						l_Cursor.getString(1), l_Cursor.getString(2),
						l_Cursor.getString(3), l_Cursor.getString(4),
						l_Cursor.getString(5), l_Cursor.getString(6),
						l_Cursor.getString(7),
						l_Cursor.getString(8));

				// l_QuestionsMap.put(Integer.valueOf(l_QuestionVO.getId()),
				// l_QuestionVO);*/

				l_QuestionsArray.put(Integer.valueOf(l_QuestionVO.getId()),
						l_QuestionVO);
			} while (l_Cursor.moveToNext());
		}
		l_Cursor.close();
		l_Database.close();
		return l_QuestionsArray;

	}
}
