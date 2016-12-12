package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME + "(" + UserContract.NewUserInfo.USER_NAME + " TEXT," +
                    UserContract.NewUserInfo.USER_NUMMERP + " TEXT,"+ UserContract.NewUserInfo.USER_NUMMERM + " TEXT," +
                    UserContract.NewUserInfo.USER_EMAIL + " TEXT,"+ UserContract.NewUserInfo.USER_STRASSE + " TEXT," +
                    UserContract.NewUserInfo.USER_PLZ + " TEXT,"+ UserContract.NewUserInfo.USER_ORT + " TEXT);";


    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created/ opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");
    }

    public void addInformations(String name, String nummerp, String nummerm, String email, String strasse, String plz, String ort, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_NUMMERP, nummerp);
        contentValues.put(UserContract.NewUserInfo.USER_NUMMERM, nummerm);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL, email);
        contentValues.put(UserContract.NewUserInfo.USER_STRASSE, strasse);
        contentValues.put(UserContract.NewUserInfo.USER_PLZ, plz);
        contentValues.put(UserContract.NewUserInfo.USER_ORT, ort);

        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_NAME};

        //cursor = db.query(table name, projection, selection, selection args, group rows, filter by row groups, sort order)
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserContract.NewUserInfo.USER_NAME, UserContract.NewUserInfo.USER_NUMMERP, UserContract.NewUserInfo.USER_NUMMERM,
                UserContract.NewUserInfo.USER_EMAIL, UserContract.NewUserInfo.USER_STRASSE, UserContract.NewUserInfo.USER_PLZ, UserContract.NewUserInfo.USER_ORT};
        String selection = UserContract.NewUserInfo.USER_NAME + " LIKE ?";                          //WHERE Bedingung
        String [] selection_args = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
