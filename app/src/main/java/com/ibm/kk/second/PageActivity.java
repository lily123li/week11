package com.ibm.kk.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity implements
        AdapterView.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        //Button btn1 = (Button) findViewById(R.id.entry_system);
        //btn1.setOnClickListener(this);

        List<String> list1 = new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("item" + i);
        }

    }

    @Override
    public void onClick(View v) {
        //startActivity(new Intent(PageActivity.this, StudentListActivity.class));
        //startActivity(new Intent(PageActivity.this, LoginActivity.class));
        //startActivity(new Intent(PageActivity.this, RegisterActivity.class));
    }

    // 创建菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.student){
            startActivity(new Intent(PageActivity.this, FrameActivity.class));
        }
        if(item.getItemId()==R.id.activity){
            startActivity(new Intent(PageActivity.this, StudentListActivity.class));
        }
        if(item.getItemId()==R.id.open_list){
            startActivity(new Intent(PageActivity.this, MyList2Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
