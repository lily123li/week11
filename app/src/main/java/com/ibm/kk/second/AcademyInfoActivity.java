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
import android.widget.Toast;

public class AcademyInfoActivity extends AppCompatActivity implements AdapterView.OnClickListener{

    private TextView aca_id;
    private TextView aca_name;
    private TextView aca_phone;
    private TextView aca_code;
    private TextView aca_address;
    private TextView aca_email;
    private Button button1;
    private Button button2;
    String aca_id2;
    String aca_name2;
    String aca_phone2;
    String aca_code2;
    String aca_address2;
    String aca_email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy_info);


        aca_id = (TextView) findViewById(R.id.et_id4);
        aca_name = (TextView) findViewById(R.id.et_name4);
        aca_phone = (TextView) findViewById(R.id.et_phone4);
        aca_code = (TextView) findViewById(R.id.et_email4);
        aca_address = (TextView) findViewById(R.id.et_address4);
        aca_email = (TextView) findViewById(R.id.et_code4);

        //获取SP里面保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("myacademy", Activity.MODE_PRIVATE);
        aca_id2 = sharedPreferences.getString("aca_id", "001");
        aca_name2 = sharedPreferences.getString("aca_name", "经济信息工程学院");
        aca_phone2 = sharedPreferences.getString("aca_phone", "87092220");
        aca_code2 = sharedPreferences.getString("aca_code", "611130");
        aca_address2 = sharedPreferences.getString("aca_address", "通博楼B座");
        aca_email2 = sharedPreferences.getString("aca_email", "xxzz@swufe.edu.cn");

        button1 = (Button)this.findViewById(R.id.aca_return);
        button1.setOnClickListener(this);
        button2 = (Button)this.findViewById(R.id.aca_config);
        button2.setOnClickListener(this);

        show();
    }

    private void show() {
        aca_id.setText(aca_id2 );
        aca_name.setText(aca_name2 );
        aca_phone.setText(aca_phone2 );
        aca_code.setText(aca_code2 );
        aca_address.setText(aca_address2);
        aca_email.setText(aca_email2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==2){

            Bundle bundle = data.getExtras();
            aca_id2 = bundle.getString("aca_id","001");
            aca_name2 = bundle.getString("aca_name","经济信息工程学院");
            aca_phone2 = bundle.getString("aca_phone","87092220");
            aca_code2 = bundle.getString("aca_code","xxzz2swufe.edu.cn");
            aca_address2 = bundle.getString("aca_address","通博楼B座");
            aca_email2 = bundle.getString("aca_email","611130");


            SharedPreferences sharedPreferences = getSharedPreferences("myacademy", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("aca_id",aca_id2);
            editor.putString("aca_name",aca_name2);
            editor.putString("aca_phone",aca_phone2);
            editor.putString("aca_code",aca_code2);
            editor.putString("aca_address",aca_address2);
            editor.putString("aca_email",aca_email2);
            editor.commit();

            show();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View btn) {

        if (btn.getId() == R.id.aca_return) {
            Intent intent1 = new Intent(this,FrameActivity.class);
            startActivity(intent1);

        } else {
            Intent intent2 = new Intent(this, AcademyActivity.class);
            intent2.putExtra("aca_id", aca_id2);
            intent2.putExtra("aca_name", aca_name2);
            intent2.putExtra("aca_phone", aca_phone2);
            intent2.putExtra("aca_code", aca_code2);
            intent2.putExtra("aca_address", aca_address2);
            intent2.putExtra("aca_email", aca_email2);
            startActivityForResult(intent2, 1);

        }
    }
}
