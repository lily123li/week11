package com.ibm.kk.first;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RateActivity extends AppCompatActivity implements Runnable{

    private final String TAG = "Rate";
    private float dollarRate = 1/6f;//double强转为float
    private float euroRate = 1/11f;//如果只有1//11，则1和11均为整数，1/11也为整数，即0，运算结果为0
    private float wonRate = 500;
    private String updateDate = "";


    EditText rmb ;
    TextView show;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rate);

        rmb = (EditText) findViewById(R.id.rmb);
        show = (TextView) findViewById(R.id.showOut);

        //获取SP里面保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //myrate为文件名
        dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        wonRate = sharedPreferences.getFloat("won_rate", 0.0f);
        updateDate = sharedPreferences.getString("update_date", "");

        //获取当前系统时间
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
        final String todayStr = sdf.format(today);//将当前日期对象转为字符串,小写的mm代表分钟

        //判断时间是否与当前系统时间相同
        if(!todayStr.equals(updateDate)){
            //开启子线程(需要更新，否则不需要)
            Thread t = new Thread(this);
            t.start();//t开始执行run方法
        }

        handler = new Handler() {//匿名类对象，主线程获得了循环队列
            @Override
            public void handleMessage(Message msg) {//当消息到来时
                if(msg.what==5){
                    Bundle bdl = (Bundle) msg.obj;
                    dollarRate = bdl.getFloat("dollar-rate");
                    euroRate = bdl.getFloat("euro-rate");
                    wonRate = bdl.getFloat("won-rate");
                    //String str = (String) msg.obj;//将object类型强转为string
                    //show.setText(str);//主线程中获取的内容放到show里
                    //提示窗

                    //保存更新的日期和汇率
                    SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat("dollar_rate",dollarRate);
                    editor.putFloat("euro_rate",euroRate);
                    editor.putFloat("won_rate",wonRate);
                    editor.putString("update_date",todayStr);
                    editor.apply();

                    Toast.makeText(RateActivity.this,"汇率已更新",Toast.LENGTH_SHORT).show();
                }

                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View btn){
        //获取用户内容
        String str = rmb.getText().toString();//用户内容转为string
        float r = 0;
        if(str.length()>0){//避免用户没有输入,空内容不能转换为double而报错
            r = Float.parseFloat(str);
        }else{
            //提示用户输入内容
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
            return;
        }

        float val = 0;
        if(btn.getId()==R.id.btn_dollar){
            val = r * dollarRate;
        }else if(btn.getId()==R.id.btn_euro){
            val = r * euroRate;
        }else{
            val = r * wonRate;
        }
        String s = String.format("%.2f",val);//结果保留两位小数
       //String s = String.valueOf(Math.round(100 * val)/100.0);
        show.setText(s);
        //show.setText(String.valueOf(val));float转为string
        //或者show.setText(val + "")
    }
    public void openOne(View btn){//响应按钮的方法
        //打开一个页面Activity
        //Intent hello = new Intent(this,Work1Activity.class);
        openConfig();
        //startActivity(config);//打开页面
        //startActivity(intent);
        //startActivity(web);
        //startActivity(hello);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
       // return super.onCreateOptionsMenu(menu);//返回真则表明当前RateActivity有menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
            openConfig();
        }else if(item.getItemId()==R.id.open_list){
            //打开列表窗口
            Intent list = new Intent(this,RateListActivity.class);
            startActivity(list);
            //测试数据库
            /*RateItem item1 = new RateItem("aaaa","123");
            RateManager manager = new RateManager(this);
            manager.add(item1);
            manager.add(new RateItem("bbbb","23.5"));
            //查询所有数据
            List<RateItem> testList = manager.listAll();
            for(RateItem i : testList){

            }*/
        }
        return super.onOptionsItemSelected(item);
    }

    private void openConfig() {
        Intent config = new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);
        startActivityForResult(config,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==2){

            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar",0.1f);
            euroRate = bundle.getFloat("key_euro",0.1f);
            wonRate = bundle.getFloat("key_won",0.1f);


            //将新设置的汇率保存到SP的myrate文件里
            SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            //或者采用以下这种，但此方式的文件名不可更改，所以只能有一个文件
            // SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.commit();//保存,apply()也可以用于保存数据，但是不需要等待，直接执行下一个操作

        }
        super.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    public void run() {
        //主线程中需要执行的代码
        for (int i = 1; i < 4; i++) {
            try {
                Thread.sleep(2000);//表示当前停止2秒，此方法可能产生异常，需要用try...catch捕获
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //用于保存获取的汇率
        Bundle bundle;


        //获取网络数据
        /*URL url = null;
        try {
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();//获得一个输入流
            //从inputstream到stream的转换
            String html = inputStream2String(in);
            Document doc = Jsoup.parse(html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        bundle = getFromBOC();
        //bundle中保存所获取的
        //获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);//5是对方收件人的标识，what表示int类型
        //msg.what = 5;
        //msg.obj = "Hello from run()";  //object是所有对象的父类
        msg.obj = bundle;//带回bundle对象
        handler.sendMessage(msg);//handler把msg发送到了队列里面
        }

        /*
        从bankofchina.htm获取数据
         */


    private Bundle getFromBOC() {
        Bundle bundle = new Bundle();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();//通过网络得到document对象
            Elements tables = doc.getElementsByTag("table");//得到了table的集合
            Element table1 = tables.get(0);
            //获取TD中的数据
            Elements tds = table1.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);
                String str1 = td1.text();
                String val = td2.text();
                if("美元".equals(str1)){
                    bundle.putFloat("dollar-rate",100f/Float.parseFloat(val));
                }else if("欧元".equals(str1)){
                    bundle.putFloat("euro-rate",100f/Float.parseFloat(val));
                }
                else if("韩元".equals(str1)){
                    bundle.putFloat("won-rate",100f/Float.parseFloat(val));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }


    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        for(;;){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }


}
