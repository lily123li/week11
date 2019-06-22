package com.ibm.kk.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity implements AdapterView.OnClickListener{

    private Button button;
    private EditText stu_id;
    private EditText stu_name;
    private EditText stu_phone;
    private EditText stu_code;
    private EditText stu_address;
    private EditText stu_email;
    private EditText stu_major;
    private String stu_id2;
    private String stu_name2;
    private String stu_phone2;
    private String stu_code2;
    private String stu_address2;
    private String stu_email2;
    private String stu_major2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        button = (Button) this.findViewById(R.id.stu_save2);
        button.setOnClickListener(this);

        Intent intent = getIntent();
        stu_id2 = intent.getStringExtra("stu_id");
        stu_name2 = intent.getStringExtra("stu_name");
        stu_phone2 = intent.getStringExtra("stu_phone");
        stu_code2 = intent.getStringExtra("stu_code");
        stu_address2 = intent.getStringExtra("stu_address");
        stu_email2 = intent.getStringExtra("stu_email");
        stu_major2 = intent.getStringExtra("stu_major");

        stu_id = (EditText) findViewById(R.id.et_id7);
        stu_name = (EditText) findViewById(R.id.et_name7);
        stu_phone = (EditText) findViewById(R.id.et_phone7);
        stu_code = (EditText) findViewById(R.id.et_email7);
        stu_address = (EditText) findViewById(R.id.et_address7);
        stu_email = (EditText) findViewById(R.id.et_code7);
        stu_major = (EditText) findViewById(R.id.et_major7);


        stu_id.setText(stu_id2 );
        stu_name.setText(stu_name2 );
        stu_phone.setText(stu_phone2 );
        stu_code.setText(stu_code2 );
        stu_address.setText(stu_address2);
        stu_email.setText(stu_email2);
        stu_major.setText(stu_major2);


    }

    @Override
    public void onClick(View btn) {
        //获取新的值
        String newid = stu_id.getText().toString();
        String newname = stu_name.getText().toString();
        String newphone = stu_phone.getText().toString();
        String newcode = stu_code.getText().toString();
        String newaddress = stu_address.getText().toString();
        String newemail = stu_email.getText().toString();
        String newmajor = stu_major.getText().toString();
        //保存到Bundle或放入到Extra
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putString("stu_id",newid);
        bdl.putString("stu_name",newname);
        bdl.putString("stu_phone",newphone);
        bdl.putString("stu_code",newcode);
        bdl.putString("stu_address",newaddress);
        bdl.putString("stu_email",newemail);
        bdl.putString("stu_major",newmajor);
        intent.putExtras(bdl);
        setResult(2,intent);
        //返回到调用页面
        finish();//结束当前页面，回到调用页面

    }
}
