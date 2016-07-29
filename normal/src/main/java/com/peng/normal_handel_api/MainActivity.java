package com.peng.normal_handel_api;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* //2秒钟后执行run方法可以更新ui

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("_________________>>两秒钟后执行");
            }
        }, 5000);*/
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hhhehhheheh");
            }
        };
        //3秒后,每隔一秒执行一次
        timer.schedule(task,5000,1000);
    }
}
