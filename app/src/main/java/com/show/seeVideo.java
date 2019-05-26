package com.show;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.ibm.kk.first.R;


public class seeVideo extends Activity {

        private VideoView video;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setContentView(R.layout.activity_see_video);

            video=(VideoView) findViewById(R.id.video);
            MediaController mc=new MediaController(seeVideo.this);// 创建一个MediaController对象
            video.setMediaController(mc);// 将VideoView与MediaController关联起来
            video.setVideoURI(Uri.parse("android.resource://com.show/" + R.raw.chuyutong));
            video.requestFocus();// 设置VideoView获取焦点
            try {
                    video.start(); // 播放视频
                 }catch(Exception e) {
                    e.printStackTrace();
                 }
            //设置VideoView的Completion事件监听器
            video.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(seeVideo.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
