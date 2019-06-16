package com.ibm.kk.second;

import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    private SensorManager sensorManager;
    private Vibrator vibrator;
    private TextView text;
    private ImageView img;
    private static String str[] = {"石头","剪刀","布"};
    private static int pics[] = {R.mipmap.bg1, R.mipmap.bg2, R.mipmap.bg3,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView)findViewById(R.id.txtlabel);
        img = (ImageView)findViewById(R.id.imageView);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);






    }
}
