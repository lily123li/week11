package com.ibm.kk.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity implements View.OnClickListener {
    TextView out;
    EditText edit;
    double result;
    double c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        out = findViewById(R.id.txtout);
        edit = findViewById(R.id.inp);
        //String str = edit.getText().toString();
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        //Log.i("main","msg...");
        String str = edit.getText().toString();
        c = Double.parseDouble(str);
        result = c * 1.8 + 32;
        String s = String.valueOf(Math.round(100 * result)/100.0);//结果保留两位小数
        out.setText("result=" + s);
        }
    }

