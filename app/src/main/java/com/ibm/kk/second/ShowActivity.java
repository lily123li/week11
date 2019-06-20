package com.ibm.kk.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sc_info);

        Intent intent = getIntent();
        SCItem scitem = (SCItem) intent.getSerializableExtra(TableContanst.STUDENT_TABLE);
        ((TextView)findViewById(R.id.tv_info_id)).setText(scitem.getId()+"");
        ((TextView)findViewById(R.id.tv_info_name)).setText(scitem.getName());
        ((TextView)findViewById(R.id.tv_info_num)).setText(scitem.getNum());
        ((TextView)findViewById(R.id.tv_info_period)).setText(scitem.getPeriod()+"");
        ((TextView)findViewById(R.id.tv_info_grade)).setText(scitem.getGrade());
        ((TextView)findViewById(R.id.tv_info_type)).setText(scitem.getType());
        ((TextView)findViewById(R.id.tv_info_train_date)).setText(scitem.getTrainDate());
        ((TextView)findViewById(R.id.tv_info_place)).setText(scitem.getPlace());
    }
    public void goBack(View view) {
        finish();
    }
}
