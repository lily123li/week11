package com.ibm.kk.second;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContentListActivity extends ListActivity implements Runnable, AdapterView.OnItemClickListener {

    String data[] = {"wait..."};
    Handler handler;
    String url;
    List<String> hrefs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_content_list);

        List<String> list1 = new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("item" + i);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(ContentListActivity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void run() {
        //获取网络数据，放入list带回到主线程中
        List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();


            Document doc = null;
            try {
                Thread.sleep(100);
                doc = Jsoup.connect("https://www.swufe.edu.cn/4778_2.html").get();
                Elements uls = doc.getElementsByTag("ul");
                Element u3 = uls.get(2);
                //获取TD中的数据
                Elements tds = u3.getElementsByTag("td");
                String a = "https://www.swufe.edu.cn/";
                for(int j=0;j<tds.size();j+=2){
                    Element td1 = tds.get(j+1);
                    Element td2 = tds.get(j+2);
                    String content = td1.text();
                    String time = td2.text();

                    String href = a + tds.attr("href");

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ItemTitle",content);
                    map.put("ItemDetail",time);
                    retList.add(map);
                    hrefs.add(href);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
        }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }
}

