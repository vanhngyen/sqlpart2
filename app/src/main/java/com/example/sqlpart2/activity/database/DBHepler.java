package com.example.sqlpart2.activity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHepler {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "USER";

    public static  String TABLE_NAME = "TBL_USER";
    public static  String ID = "_id";
    public static  String NAME = "name";
    public static  String GENDER = "gender";
    public static  String DES = "des";
    public  DBHepler(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABLE_NAME+"("+
            ID+ "INTEGER PRIMARY KEY,"+
            NAME+ "TEXT,"+
            GENDER +"TEXT ,"+
            DES +"TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql ="DROP  TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public  String addUser( String user , String gender, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        long isAdd = db.insert(TABLE_NAME,null,contentValues);
        if (isAdd== -1){
            return  "Add fail";
        }
        db.close();
        return  "add success";
    }


    public String updateUser( int id , String user, String  gender , String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        int isUpdate  = db.update(TABLE_NAME,contentValues,ID +"=? ", new  String[]{id+""});

        if (isUpdate>0){
            return  "Update success";
        }
        db.close();
        return  "Update fail";
    }

    public  String deleteUser(int id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDelete = db.delete(TABLE_NAME,ID+"=?", new String[]{id+""});
        if (isDelete>0){
            return  "Delete success";
        }
        db.close();
        return  "Delete fail";
    }
    public Cursor getAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = " SELECT * FROM " +TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

}
