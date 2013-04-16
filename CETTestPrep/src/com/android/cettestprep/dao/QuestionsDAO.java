package com.android.cettestprep.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.util.SparseArray;

import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.constant.SubjectsEnum;
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
			SubjectsEnum f_Subject) {

		String l_TableName = "";
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
		}
		String l_Query = "SELECT  * FROM " + l_TableName;

		return getQuestions(l_Query);
	}

	public SparseArray<Parcelable> getQuestions(String f_Query) {
		SparseArray<Parcelable> l_QuestionsArray = new SparseArray<Parcelable>();

		// Map<Integer,QuestionsVO> l_QuestionsMap = new HashMap<Integer,
		// QuestionsVO>();
		SQLiteDatabase l_Database = this.getWritableDatabase();
		Cursor cursor = l_Database.rawQuery(f_Query, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionsVO l_QuestionVO = new QuestionsVO(
						Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6),
						cursor.getString(7),
						cursor.getString(8).toCharArray()[0]);

				// l_QuestionsMap.put(Integer.valueOf(l_QuestionVO.getId()),
				// l_QuestionVO);*/

				l_QuestionsArray.put(Integer.valueOf(l_QuestionVO.getId()),
						l_QuestionVO);
			} while (cursor.moveToNext());
		}

		return l_QuestionsArray;

	}
}
