package com.ibm.kk.second;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class StudentInfoActivity extends AppCompatActivity implements AdapterView.OnClickListener{

    private TextView stu_id;
    private TextView stu_name;
    private TextView stu_phone;
    private TextView stu_code;
    private TextView stu_address;
    private TextView stu_email;
    private TextView stu_major;
    private Button button1;
    private Button button2;
    String stu_id2;
    String stu_name2;
    String stu_phone2;
    String stu_code2;
    String stu_address2;
    String stu_email2;
    String stu_major2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        stu_id = (TextView) findViewById(R.id.et_id6);
        stu_name = (TextView) findViewById(R.id.et_name6);
        stu_phone = (TextView) findViewById(R.id.et_phone6);
        stu_code = (TextView) findViewById(R.id.et_email6);
        stu_address = (TextView) findViewById(R.id.et_address6);
        stu_email = (TextView) findViewById(R.id.et_code6);
        stu_major = (TextView) findViewById(R.id.et_major6);

        //获取SP里面保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("mystu", Activity.MODE_PRIVATE);
        stu_id2 = sharedPreferences.getString("stu_id", "41724026");
        stu_name2 = sharedPreferences.getString("stu_name", "Jenny");
        stu_phone2 = sharedPreferences.getString("stu_phone", "17389423166");
        stu_code2 = sharedPreferences.getString("stu_code", "2017级");
        stu_address2 = sharedPreferences.getString("stu_address", "柳林校区榕园");
        stu_email2 = sharedPreferences.getString("stu_email", "jn@swufe.edu.cn");
        stu_major2 = sharedPreferences.getString("stu_major", "金信实验班");

        button1 = (Button)this.findViewById(R.id.stu_return);
        button1.setOnClickListener(this);
        button2 = (Button)this.findViewById(R.id.stu_config);
        button2.setOnClickListener(this);

        show();

    }

    private void show() {
        stu_id.setText(stu_id2 );
        stu_name.setText(stu_name2 );
        stu_phone.setText(stu_phone2 );
        stu_code.setText(stu_code2 );
        stu_address.setText(stu_address2);
        stu_email.setText(stu_email2);
        stu_major.setText(stu_major2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==2){

            Bundle bundle = data.getExtras();
            stu_id2 = bundle.getString("stu_id","41724026");
            stu_name2 = bundle.getString("stu_name","Jenny");
            stu_phone2 = bundle.getString("stu_phone","17389423166");
            stu_code2 = bundle.getString("stu_code","2017级");
            stu_address2 = bundle.getString("stu_address","柳林校区榕园");
            stu_email2 = bundle.getString("stu_email","jn@swufe.edu.cn");
            stu_major2 = bundle.getString("stu_major","金信实验班");

            SharedPreferences sharedPreferences = getSharedPreferences("mystu", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("stu_id",stu_id2);
            editor.putString("stu_name",stu_name2);
            editor.putString("stu_phone",stu_phone2);
            editor.putString("stu_code",stu_code2);
            editor.putString("stu_address",stu_address2);
            editor.putString("stu_email",stu_email2);
            editor.putString("stu_major",stu_major2);
            editor.commit();

            show();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }



    @Override
    public void onClick(View btn) {
        if (btn.getId() == R.id.stu_return) {
            Intent intent1 = new Intent(this,FrameActivity.class);
            startActivity(intent1);

        } else {
            Intent intent2 = new Intent(this, StudentActivity.class);
            intent2.putExtra("stu_id", stu_id2);
            intent2.putExtra("stu_name", stu_name2);
            intent2.putExtra("stu_phone", stu_phone2);
            intent2.putExtra("stu_code", stu_code2);
            intent2.putExtra("stu_address", stu_address2);
            intent2.putExtra("stu_email", stu_email2);
            intent2.putExtra("stu_major", stu_major2);
            startActivityForResult(intent2, 1);

        }
    }
}
