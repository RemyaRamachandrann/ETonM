package com.android.cettestprep.dao;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionsDatabaseHandler extends SQLiteOpenHelper {
	
	    private static final int DATABASE_VERSION = 1;

	
		private static final String DATABASE_NAME = "";

		// Question Bank table name
		private static final String TABLE_QUESTIONS = "questionBank";
		
		// Question Bank Table Columns names
		private static final String KEY_ID = "id";
		
		private final Context m_Context;
		 
		private SQLiteDatabase m_QuestionsDataBase;
		
		private String m_DBPath;
		

	public QuestionsDatabaseHandler(Context f_context) {
		super(f_context, DATABASE_NAME, null, DATABASE_VERSION);
		m_DBPath = f_context.getDatabasePath(DATABASE_NAME).getAbsolutePath();
		this.m_Context = f_context;

	}

	@Override
	public void onCreate(SQLiteDatabase f_DB) {
		// No Need to create as the DB will be copied initially and not created

	}

	@Override
	public void onUpgrade(SQLiteDatabase f_DB, int f_OldVersion, int f_NewVersion) {
		f_DB.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
		// TODO Auto-generated method stub

	}
	
	public List<String> getAllQuestionsByYear(int f_Year){
		/*
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;*/
		
		return null;
		
	}
	
	public List<String> getAllQuestionsBySubject(String f_Subject){
		
		return null;
	}

}
