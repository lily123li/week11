package com.ibm.kk.first.StudentDBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ibm.kk.first.TableContanst.TableContanst;


public class StudentDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "StudentDBHelper";
    public static final String DB_NAME = "student_manager.db";
    public static final int VERSION = 1;    //构造方法
    public StudentDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    public StudentDBHelper(Context context) {
        this(context, DB_NAME, null, VERSION);     }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate");
        db.execSQL("create table "
                +TableContanst.STUDENT_TABLE+ "(_id Integer primary key AUTOINCREMENT,"
                + "name char,age integer, sex char, likes char, phone_number char,train_date date, "
                + "modify_time DATETIME)");     }
    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onUpgrade");
    }

}
