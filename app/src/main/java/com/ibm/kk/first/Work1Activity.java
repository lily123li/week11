package com.ibm.kk.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Work1Activity extends AppCompatActivity {
    TextView score;
    TextView score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwork1);
        score = (TextView) findViewById(R.id.score);
        score2 = (TextView) findViewById(R.id.score2);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){//onPause之后多出来一个onSaveInstanceState保留旋转屏幕前的数据
        super.onSaveInstanceState(outState);
        String scorea =  ((TextView) findViewById(R.id.score)).getText().toString();//获得当前分数
        String scoreb = ((TextView) findViewById(R.id.score2)).getText().toString();
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//onStart之后多出来一个onRestoreInstanceState还原内容
        super.onRestoreInstanceState(savedInstanceState);
        String scorea = savedInstanceState.getString("teama_score");
        String scoreb = savedInstanceState.getString("teamb_score");
        ((TextView) findViewById(R.id.score)).setText(scorea);
        ((TextView) findViewById(R.id.score2)).setText(scoreb);
    }

    public void btnAdd1(View btn) {
        if(btn.getId()==R.id.btn_1) {
            showScore(3);
        }else{
            showScore2(3);
        }
    }
    public void btnAdd2(View btn) {
        if(btn.getId()==R.id.btn_2) {
            showScore(2);
        }else{
            showScore2(2);
        }
    }
    public void btnAdd3(View btn) {
        if(btn.getId()==R.id.btn_3) {
            showScore(1);
        }else{
            showScore2(1);
        }
    }
    public void btnReset(View btn) {
        score.setText("0");
        score2.setText("0");
    }
    private void showScore(int inc){
        String olsScore = (String)score.getText();
        int newScore = Integer.parseInt(olsScore) + inc;
        score.setText("" + newScore);
    }
    private void showScore2(int inc){
        String olsScore = (String)score2.getText();
        int newScore = Integer.parseInt(olsScore) + inc;
        score2.setText("" + newScore);
    }
}
