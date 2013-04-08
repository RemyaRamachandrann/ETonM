package com.android.cettestprep.dao;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	    private static final int DATABASE_VERSION = 1;

	
		private static final String DATABASE_NAME = "";

		// Contacts table name
		private static final String TABLE_CONTACTS = "contacts";
		

	public DatabaseHandler(Context f_context) {
		super(f_context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase f_arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase f_arg0, int f_arg1, int f_arg2) {
		// TODO Auto-generated method stub

	}
	
	public List<String> getAllQuestionsByYear(int f_Year){
		
		return null;
		
	}
	
	public List<String> getAllQuestionsBySubject(String f_Subject){
		
		return null;
	}

}
