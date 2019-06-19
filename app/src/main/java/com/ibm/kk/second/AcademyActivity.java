package com.ibm.kk.second;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AcademyActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    private Button button;
    private TextView aca_id;
    private TextView aca_name;
    private TextView aca_phone;
    private TextView aca_code;
    private TextView aca_address;
    private TextView aca_email;
    private String aca_id2;
    private String aca_name2;
    private String aca_phone2;
    private String aca_code2;
    private String aca_address2;
    private String aca_email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);

        aca_id = (TextView) findViewById(R.id.et_id5);
        aca_name = (TextView) findViewById(R.id.et_name5);
        aca_phone = (TextView) findViewById(R.id.et_phone5);
        aca_code = (TextView) findViewById(R.id.et_email5);
        aca_address = (TextView) findViewById(R.id.et_address5);
        aca_email = (TextView) findViewById(R.id.et_code5);

        button = (Button) this.findViewById(R.id.aca_button2);
        button.setOnClickListener(this);


        //获取SP里面保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("myacademy", Activity.MODE_PRIVATE);
        aca_id2 = sharedPreferences.getString("aca_id", "001");
        aca_name2 = sharedPreferences.getString("aca_name", "001");
        aca_phone2 = sharedPreferences.getString("aca_phone", "001");
        aca_code2 = sharedPreferences.getString("aca_code", "001");
        aca_address2 = sharedPreferences.getString("aca_address", "001");
        aca_email2 = sharedPreferences.getString("aca_email", "001");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==2){

            Bundle bundle = data.getExtras();
            aca_id2 = bundle.getString("aca_id","001");
            aca_name2 = bundle.getString("aca_name","001");
            aca_phone2 = bundle.getString("aca_phone","001");
            aca_code2 = bundle.getString("aca_code","001");
            aca_address2 = bundle.getString("aca_address","001");
            aca_email2 = bundle.getString("aca_email","001");


            SharedPreferences sharedPreferences = getSharedPreferences("myacademy", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("aca_id",aca_id2);
            editor.putString("aca_name",aca_name2);
            editor.putString("aca_phone",aca_phone2);
            editor.putString("aca_code",aca_code2);
            editor.putString("aca_address",aca_address2);
            editor.putString("aca_email",aca_email2);
            editor.commit();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    private void openAcademyInfo() {
        Intent intent = new Intent(this, AcademyInfoActivity.class);
        intent.putExtra("aca_id", aca_id.getText().toString());
        intent.putExtra("aca_name", aca_name.getText().toString());
        intent.putExtra("aca_phone", aca_phone.getText().toString());
        intent.putExtra("aca_code", aca_code.getText().toString());
        intent.putExtra("aca_address", aca_address.getText().toString());
        intent.putExtra("aca_email", aca_email.getText().toString());
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View btn) {
        if (btn.getId() == R.id.aca_button2) {
            openAcademyInfo();
        }


    }
}
