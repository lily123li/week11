package com.ibm.kk.second;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AcademyInfoActivity extends AppCompatActivity implements AdapterView.OnClickListener{

    private EditText aca_id;
    private EditText aca_name;
    private EditText aca_phone;
    private EditText aca_code;
    private EditText aca_address;
    private EditText aca_email;
    private Button button;
    private Button button4;
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


        aca_id = (EditText) findViewById(R.id.et_id4);
        aca_name = (EditText) findViewById(R.id.et_name4);
        aca_phone = (EditText) findViewById(R.id.et_phone4);
        aca_code = (EditText) findViewById(R.id.et_email4);
        aca_address = (EditText) findViewById(R.id.et_address4);
        aca_email = (EditText) findViewById(R.id.et_code4);

        button = (Button)this.findViewById(R.id.aca_button1);
        button.setOnClickListener(this);
        button4 = (Button)this.findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Intent intent = getIntent();
        aca_id2 = intent.getStringExtra("aca_id");
        aca_name2 = intent.getStringExtra("aca_name");
        aca_phone2 = intent.getStringExtra("aca_phone");
        aca_code2 = intent.getStringExtra("aca_code");
        aca_address2 = intent.getStringExtra("aca_address");
        aca_email2 = intent.getStringExtra("aca_email");


        aca_id.setText(aca_id2 );
        aca_name.setText(aca_name2 );
        aca_phone.setText(aca_phone2 );
        aca_code.setText(aca_code2 );
        aca_address.setText(aca_address2);
        aca_email.setText(aca_email2);

    }


    @Override
    public void onClick(View btn) {

        if (btn.getId() == R.id.aca_button1) {
            Intent intent1 = new Intent(this,AcademyActivity.class);
            startActivity(intent1);

        } else {

            //获取新的值
            String newid = aca_id.getText().toString();
            String newname = aca_name.getText().toString();
            String newphone = aca_phone.getText().toString();
            String newcode = aca_code.getText().toString();
            String newaddress = aca_address.getText().toString();
            String newemail = aca_email.getText().toString();
            //保存到Bundle或放入到Extra
            Intent intent = getIntent();
            Bundle bdl = new Bundle();
            bdl.putString("aca_id",newid);
            bdl.putString("aca_name",newname);
            bdl.putString("aca_phone",newphone);
            bdl.putString("aca_code",newcode);
            bdl.putString("aca_address",newaddress);
            bdl.putString("aca_email",newemail);
            intent.putExtras(bdl);
            setResult(2,intent);
            //返回到调用页面
            finish();//结束当前页面，回到调用页面
        }
    }
}
