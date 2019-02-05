package com.ahujafabrics.yarnit.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelper";

    private static final String UserTable = "UserProfile";

    public SQLiteHelper(Context context){
        super(context, UserTable, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + UserTable + " (" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserName TEXT, " +
                "Address1 TEXT, " +
                "Address2 TEXT, " +
                "City TEXT, " +
                "Phone INTEGER, " +
                "Email TEXT, " +
                "Role TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addData(UserProfile item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", item.getUserName());
        contentValues.put("Address1", item.getAddress1());
        contentValues.put("Address2", item.getAddress2());
        contentValues.put("City", item.getCity());
        contentValues.put("Phone", item.getPhone());
        contentValues.put("Email", item.getEmail());
        contentValues.put("Role", item.getRole());

        Log.d(TAG, "addData: Adding " + item + " to " + UserTable);

        long result = db.insert(UserTable, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIfProfileExists(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + UserTable;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0)
            return true;

        return false;
    }

    public Cursor getProfile(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + UserTable;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
