package com.ibm.kk.first;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements Runnable, AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private String TAG = "mylist2";
    Handler handler;
    private List<HashMap<String, String>> listItems;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();//初始化方法
        this.setListAdapter(listItemAdapter);
        //MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,listItems);
        //this.setListAdapter(myAdapter);//调用adapter


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
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "Rate： " + i); // 标题文字
            map.put("ItemDetail", "detail" + i); // 详情描述
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
    public void run(){
        //获取网络数据，放入list带回到主线程中
        List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            Thread.sleep(1000);
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Elements tables = doc.getElementsByTag("table");
            Element table1 = tables.get(0);
            //获取TD中的数据
            Elements tds = table1.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);
                String str1 = td1.text();
                String val = td2.text();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //通过map获得数据
        HashMap<String, String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");//点击行，获得相应的数据项，通过行获得数据
        //通过view获得数据
        TextView title = (TextView)view.findViewById(R.id.itemTitle);
        TextView detail = (TextView)view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());

        //打开新的页面，传入参数
        Intent rateCalc = new Intent(this,RateCalActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(rateCalc);




    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        })
                .setNegativeButton("否",null);
        builder.create().show();

        return true;
    }
}
