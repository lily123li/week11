package com.ibm.kk.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra(TableContanst.STUDENT_TABLE);
        ((TextView)findViewById(R.id.tv_info_id)).setText(student.getId()+"");
        ((TextView)findViewById(R.id.tv_info_name)).setText(student.getName());
        ((TextView)findViewById(R.id.tv_info_num)).setText(student.getNum());
        ((TextView)findViewById(R.id.tv_info_period)).setText(student.getPeriod()+"");
        ((TextView)findViewById(R.id.tv_info_grade)).setText(student.getGrade());
        ((TextView)findViewById(R.id.tv_info_type)).setText(student.getType());
        ((TextView)findViewById(R.id.tv_info_train_date)).setText(student.getTrainDate());
        ((TextView)findViewById(R.id.tv_info_place)).setText(student.getPlace());
    }
    public void goBack(View view) {
        finish();
    }
}
