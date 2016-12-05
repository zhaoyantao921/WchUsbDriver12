package com.wch.wchusbdriver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库类
 * ID   DATE    CONTENT
 * insert()
 * query()
 * delete()
 */
public class DBHelper {
    private static final String DATABASE_NAME="mDB";//数据库名字
    private static final int DATA_VERSION=1;//数据库版本
    private static final String TABLE_NAME="mtable";//保存表名称；
    private static final String ID="_id";
    private static final String DATE="mdate";
    private static final String CONTENT="mcontent";
    private SQLiteDatabase db;
    /*内部类*/
    private DBOpenHelper helper;//继承于SQLiteOpenHelper类
    private class DBOpenHelper extends SQLiteOpenHelper{
        private static final String CREATE_TABLE="create table"+TABLE_NAME+"("
                +ID+"integer primary key autoincrement,"+DATE+"text not null,"+CONTENT+"text not null);";

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME,null,DATA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //设置数据库的初始值；
            ContentValues contentValues=new ContentValues();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            contentValues.put(DATE,dateFormat.format(new Date()));
            contentValues.put(CONTENT,"000000000000");
            db.insert(TABLE_NAME,null,contentValues);
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists"+TABLE_NAME);
            onCreate(db);

        }
    }
    /*构造方法*/
    public DBHelper(Context context){
        helper=new DBOpenHelper(context);
        db=helper.getWritableDatabase();
    }
    //插入方法
    public void insert(ConData conData){
        ContentValues  values=new ContentValues();
        values.put(DATE,conData.getDATE());
        values.put(CONTENT,conData.getContent());
        db.insert(TABLE_NAME,null,values);
    }



}