package com.ibm.kk.second;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Search extends AppCompatActivity implements AdapterView.OnClickListener {
    private EditText nameText;
    private Button button;
    private Button reButton;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private SCManager sc;
    private Button returnButton;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        nameText = (EditText) findViewById(R.id.et_srarch);
        layout=(LinearLayout) findViewById(R.id.linersearch);
        button = (Button) findViewById(R.id.bn_sure_search);
        reButton = (Button) findViewById(R.id.bn_return);
        listView = (ListView) findViewById(R.id.searchListView);
        returnButton = (Button) findViewById(R.id.return_id);
        sc = new SCManager(this);


        reButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == button) {
            reButton.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            nameText.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            String name = nameText.getText().toString();
            cursor = sc.findSC(name);
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "无相关信息！", Toast.LENGTH_SHORT).show();
            } else
                //如果有所查询的信息，则将查询结果显示出来
                adapter = new SimpleCursorAdapter(this, R.layout.find_student_list_item,
                        cursor, new String[] { TableContanst.StudentColumns.ID,
                        TableContanst.StudentColumns.NAME,
                        TableContanst.StudentColumns.NUM,
                        TableContanst.StudentColumns.PERIOD,
                        TableContanst.StudentColumns.GRADE,
                        TableContanst.StudentColumns.TYPE,
                        TableContanst.StudentColumns.PLACE,
                        TableContanst.StudentColumns.TRAIN_DATE },
                        new int[] {
                                R.id.tv_pro_id,
                                R.id.tv_pro_name,
                                R.id.tv_pro_num,
                                R.id.tv_pro_period,
                                R.id.tv_pro_grade,
                                R.id.tv_pro_type,
                                R.id.tv_pro_place,
                                R.id.tv_pro_traindate });
            listView.setAdapter(adapter);
        }else if(v==reButton|v==returnButton){
            finish();
        }
    }
}
