package com.ibm.kk.first.show.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ibm.kk.first.R;
import com.ibm.kk.first.Student.Student;
import com.ibm.kk.first.TableContanst.TableContanst;

public class ShowStudentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra(TableContanst.STUDENT_TABLE);
        ((TextView)findViewById(R.id.tv_info_id)).setText(student.getId()+"");
        ((TextView)findViewById(R.id.tv_info_name)).setText(student.getName());
        ((TextView)findViewById(R.id.tv_info_age)).setText(student.getAge()+"");
        ((TextView)findViewById(R.id.tv_info_sex)).setText(student.getSex());
        ((TextView)findViewById(R.id.tv_info_likes)).setText(student.getLike());
        ((TextView)findViewById(R.id.tv_info_train_date)).setText(student.getTrainDate());
        ((TextView)findViewById(R.id.tv_info_phone)).setText(student.getPhoneNumber());
    }
    public void goBack(View view) {
        finish();
    }

}
