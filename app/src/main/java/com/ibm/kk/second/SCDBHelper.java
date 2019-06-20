package com.ibm.kk.second;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SCDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "student_manager.db";
    public static final String TB_NAME = "tb_student";
    public static final int VERSION = 1;

    public SCDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SCDBHelper(Context context) { this(context, DB_NAME, null, VERSION); }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + Table.STUDENT_TABLE + "(_id Integer primary key AUTOINCREMENT,"
                + "name char,num char, period char, grade char, type char, place char,train_date date, "
                + "modify_time DATETIME)");     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


