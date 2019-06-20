package com.ibm.kk.second;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCManager {//对数据库进行管理，对外提供方法
    private SCDBHelper dbHelper;
    private String TBNAME;

    public SCManager(Context context) {
        dbHelper = new SCDBHelper(context);
        TBNAME = SCDBHelper.TB_NAME;
    }

    public long add(SCItem s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.StudentColumns.NAME, s.getName());
        values.put(Table.StudentColumns.NUM, s.getNum());
        values.put(Table.StudentColumns.PERIOD, s.getPeriod());
        values.put(Table.StudentColumns.GRADE, s.getGrade());
        values.put(Table.StudentColumns.TYPE, s.getType());
        values.put(Table.StudentColumns.PLACE, s.getPlace());
        values.put(Table.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(Table.StudentColumns.MODIFY_TIME, s.getModifyDateTime());
        return db.insert(Table.STUDENT_TABLE, null, values);
    }


    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(Table.STUDENT_TABLE,
                Table.StudentColumns.ID + "=?", new String[] { id + "" });
    }

    public int update(SCItem s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.StudentColumns.NAME, s.getName());
        values.put(Table.StudentColumns.NUM, s.getNum());
        values.put(Table.StudentColumns.PERIOD, s.getPeriod());
        values.put(Table.StudentColumns.GRADE, s.getGrade());
        values.put(Table.StudentColumns.TYPE, s.getType());
        values.put(Table.StudentColumns.PLACE, s.getPlace());
        values.put(Table.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(Table.StudentColumns.MODIFY_TIME, s.getModifyDateTime());
        return db.update(Table.STUDENT_TABLE, values,
                Table.StudentColumns.ID + "=?", new String[] { s.getId() + "" });
    }

    public List<Map<String,Object>> listAll() {

        List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Table.STUDENT_TABLE, null, null, null,
                null, null, Table.StudentColumns.MODIFY_TIME+" desc");

        while(cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>(8);
            map.put(Table.StudentColumns.ID, cursor.getInt(cursor.getColumnIndex(Table.StudentColumns.ID)));
            map.put(Table.StudentColumns.NAME, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.NAME)));
            map.put(Table.StudentColumns.NUM, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.NUM)));
            map.put(Table.StudentColumns.PERIOD, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.PERIOD)));
            map.put(Table.StudentColumns.GRADE, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.GRADE)));
            map.put(Table.StudentColumns.TYPE, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.TYPE)));
            map.put(Table.StudentColumns.PLACE, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.PLACE)));
            map.put(Table.StudentColumns.TRAIN_DATE, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.TRAIN_DATE)));
            map.put(Table.StudentColumns.MODIFY_TIME, cursor.getString(cursor.getColumnIndex(Table.StudentColumns.MODIFY_TIME)));
            data.add(map);
        }
        return data;
    }

    public Cursor findSC(String name){
        Cursor cursor = dbHelper.getWritableDatabase().query(Table.STUDENT_TABLE,  null, "name like ?",
                new String[] { "%" + name + "%" }, null, null, null,null);
        return cursor;      }

    public SCItem getStudentFromView(View view, long id) {
        TextView nameView = (TextView) view.findViewById(R.id.tv_pro_name);
        TextView numView = (TextView) view.findViewById(R.id.tv_pro_num);
        TextView periodView = (TextView) view.findViewById(R.id.tv_pro_period);
        TextView gradeView = (TextView) view.findViewById(R.id.tv_pro_grade);
        TextView typeView = (TextView) view.findViewById(R.id.tv_pro_type);
        TextView placeView = (TextView) view.findViewById(R.id.tv_pro_place);
        TextView dataView = (TextView) view.findViewById(R.id.tv_pro_traindate);
        String name = nameView.getText().toString();
        String num = numView.getText().toString();
        String grade = gradeView.getText().toString();
        String period = periodView.getText().toString();
        String type = typeView.getText().toString();
        String place = placeView.getText().toString();
        String data = dataView.getText().toString();
        SCItem sc = new SCItem(id, name, num, period, grade, type, place, data,null);
        return
                sc;
    }
}

