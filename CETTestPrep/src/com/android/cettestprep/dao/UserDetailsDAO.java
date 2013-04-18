package com.android.cettestprep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.cettestprep.constant.Constants;
import com.android.cettestprep.vo.UserDetailsVO;

public class UserDetailsDAO extends SQLiteOpenHelper {
	public UserDetailsDAO(Context f_context) {
		super(f_context, Constants.DATABASE_NAME, null,
				Constants.DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase f_DB) {
		// No Need to create as the DB will be copied initially and not created

	}

	@Override
	public void onUpgrade(SQLiteDatabase f_DB, int f_OldVersion,
			int f_NewVersion) {
		f_DB.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_USER);

	}

	public String getOneField(String f_FieldName) {

		String l_Query = "SELECT " + f_FieldName + " FROM "
				+ Constants.TABLE_USER;
		SQLiteDatabase l_Database = this.getWritableDatabase();
		Cursor l_Cursor = l_Database.rawQuery(l_Query, null);

		// Only a single user would be there
		if (!l_Cursor.moveToFirst()) {
			return "";
		}
		return l_Cursor.getString(0);
	}

	public UserDetailsVO getVerificationDetails() {

		String l_Query = "SELECT " + Constants.KEY_PHONENO + ","
				+ Constants.KEY_EMAILID + "," + Constants.KEY_VERIFY + " FROM "
				+ Constants.TABLE_USER;
		SQLiteDatabase l_Database = this.getWritableDatabase();
		Cursor l_Cursor = l_Database.rawQuery(l_Query, null);

		UserDetailsVO l_UserDetailsVO = new UserDetailsVO();
		// Only a single user would be there
		if (!l_Cursor.moveToFirst()) {
			return null;
		}
		l_UserDetailsVO.setMobileNo(l_Cursor.getString(0));
		l_UserDetailsVO.setEmailID(l_Cursor.getString(1));
		l_UserDetailsVO.setVerificationCode(l_Cursor.getString(2));
		return l_UserDetailsVO;
	}

	public void addUser(UserDetailsVO f_UserDetails) {
		SQLiteDatabase l_DB = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Constants.KEY_USERID, f_UserDetails.getUserID());
		values.put(Constants.KEY_USERNAME, f_UserDetails.getUserName());
		values.put(Constants.KEY_PHONENO, f_UserDetails.getMobileNo());
		values.put(Constants.KEY_EMAILID, f_UserDetails.getEmailID());
		values.put(Constants.KEY_PASSWORD, f_UserDetails.getPassword());
		values.put(Constants.KEY_CREATIONDATE, f_UserDetails.getCreationDate());
		values.put(Constants.KEY_LASTLOGGED, f_UserDetails.getLastLoggedTime());
		values.put(Constants.KEY_VERIFY, f_UserDetails.getVerificationCode());
		values.put(Constants.KEY_CONFIRM, f_UserDetails.isVerified());

		// Inserting Row
		l_DB.insert(Constants.TABLE_USER, null, values);
		l_DB.close(); // Closing database connection
	}
	
	
		public int updateConfirmation() {
			SQLiteDatabase l_DB = this.getWritableDatabase();

			ContentValues l_Values = new ContentValues();
			l_Values.put(Constants.KEY_CONFIRM, "true");

			// updating row
			return l_DB.update(Constants.TABLE_USER, l_Values, null, null);
		}

}
