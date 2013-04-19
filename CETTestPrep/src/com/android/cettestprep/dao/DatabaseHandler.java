package com.android.cettestprep.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.cettestprep.constant.Constants;

public class DatabaseHandler extends SQLiteOpenHelper {

	private final Context myContext;
	 
	private SQLiteDatabase myDataBase;
	
	private String m_DBPath;

	public DatabaseHandler(Context context) {
		super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
		m_DBPath = context.getDatabasePath(Constants.DATABASE_NAME).getAbsolutePath();
		
		this.myContext = context;
	}
	
	 /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
    	boolean dbExist = checkDataBase();
 
    	if(!dbExist){
    		//By calling this method an empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getWritableDatabase();
 
        	try {
    			copyDataBase();
    		} catch (IOException e) {
    			//Kill Exception
        	}
        	//openDataBase();
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase(){
 
    	SQLiteDatabase l_DB = null;
        boolean l_Exists = false;
    	try{
    		l_DB = SQLiteDatabase.openDatabase(m_DBPath, null, SQLiteDatabase.OPEN_READONLY);
    		/*String l_TableName = SQLiteDatabase.findEditTable(Constants.TABLE_PHYSICS);
    		Log.d("Table Name : ",l_TableName);*/
    	}catch(SQLiteException e){
    		//database does't exist yet.
    		return false;
    	} finally{
    		if(l_DB != null){
        		l_DB.close();
        		l_Exists = true;
        	}
    	}
    	
    	return l_Exists;
    	
    }
 
    public void openDataBase(){
    	try {
    	//Open the database
    	myDataBase = SQLiteDatabase.openDatabase(m_DBPath, null, SQLiteDatabase.OPEN_READONLY);
    	myDataBase.close();
     	}catch(SQLException sqle){
     
     	}
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}

	/**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(Constants.DATABASE_NAME);
    	// Path to the just created empty db
    	String outFileName = m_DBPath;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }

	@Override
	public void onCreate(SQLiteDatabase f_arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase f_arg0, int f_arg1, int f_arg2) {
		// TODO Auto-generated method stub
		
	}

}