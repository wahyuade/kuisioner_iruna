package com.wahyuade.kuisioner.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wahyuade on 10/08/17.
 */

public class DatabaseService extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kuisioner_iruna";

    private static final String TABLE_USERS = "users";
    private static final String USERS_ID = "id";
    private static final String USERS_EMAIL = "email";

    public DatabaseService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                USERS_ID + " INTEGER PRIMARY KEY," +
                USERS_EMAIL + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean setUsersData(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues user = new ContentValues();
        user.put(USERS_EMAIL, email);
        db.insert(TABLE_USERS, null, user);
        db.close();

        return true;
    }

    public void unSetUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,0,0);
    }

    public String getEmail(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{USERS_EMAIL},USERS_ID+"="+1,null,null,null,null,null);
        cursor.moveToFirst();
        db.close();
        return cursor.getString(0);
    }
}

