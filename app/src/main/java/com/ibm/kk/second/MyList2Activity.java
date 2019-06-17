package com.ibm.kk.second;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements Runnable{

    Handler handler;
    private List<HashMap<String, String>> listItems;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this); // 创建新线程
        t.start(); // 开启线程

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    listItems = (List< HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(MyList2Activity.this, listItems,
                            R.layout.list_item, // ListItem 的 XML 布局实现
                            new String[] { "ItemTitle", "ItemDetail" },//key
                            new int[] { R.id.itemTitle, R.id.itemDetail }//key与title的一一对应关系
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "content： " + i); // 标题文字
            map.put("ItemDetail", "time" + i); // 详情描述
            listItems.add(map);
        }
        // 生成适配器的 Item 和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems 数据源
                R.layout.list_item, // ListItem 的 XML 布局实现
                new String[] { "ItemTitle", "ItemDetail" },//key
                new int[] { R.id.itemTitle, R.id.itemDetail }//key与title的一一对应关系
        );
    }

    @Override
    public void run() {
        //获取网络数据，放入list带回到主线程中
        List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            Thread.sleep(1000);
            doc = Jsoup.connect("https://www.swufe.edu.cn/4778_2.html").get();
            Elements tables = doc.getElementsByTag("table");
            Element td5 = tables.get(4);
            //获取TD中的数据
            Elements tds = td5.getElementsByTag("td");

            for(int j=0;j<tds.size();j+=3){

                Element td1 = tds.get(j+1);
                Element td2 = tds.get(j+2);
                String content = td1.text();
                String time = td2.text();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemTitle",content);
                map.put("ItemDetail",time);
                retList.add(map);
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
}
