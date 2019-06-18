package com.ibm.kk.second;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ActivityListActivity extends AppCompatActivity implements Runnable{


    private String content = "";
    private String time = "";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //获取SP里面保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("myactivity", Activity.MODE_PRIVATE);
        content = sharedPreferences.getString("content",content);
        time = sharedPreferences.getString("time",time);

        //开启子线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==5){
                    Bundle bdl = (Bundle) msg.obj;
                    content = bdl.getString("content",content);
                    time = bdl.getString("time",time);
                }
                super.handleMessage(msg);
            }
        };
    }
    public void run() {
        for(int i=1;i<3;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //获取Msg对象，用于返回主线程
            Message msg = handler.obtainMessage(5);
            msg.obj = "Hello from run()";
            handler.sendMessage(msg);


            //获取网络数据
            Bundle bundle = new Bundle();
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.swufe.edu.cn/4778.html").get();
                Elements uls = doc.getElementsByTag("ul");
                Element u3 = uls.get(2);
                //获取TD中的数据
                Elements tds = u3.getElementsByTag("td");
                for(int j=0;j<tds.size();j+=2){
                    Element td1 = tds.get(j+1);
                    Element td2 = tds.get(j+2);
                    String content = td1.text();
                    String time = td2.text();
                    //保存更新的数据
                    SharedPreferences sp = getSharedPreferences("myactivity", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("content",content);
                    editor.putString("time",time);
                    editor.apply();

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}
