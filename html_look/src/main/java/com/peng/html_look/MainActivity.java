package com.peng.html_look;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView tv_result;
    private EditText et_path;
    private  Handler handler;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et_path = (EditText) findViewById(R.id.et_path);
        handler = new Handler();

    }

    public  void click(View view){

        new Thread(){public void run()
        {
            html();

        }
        }.start();

    }

    private void html() {
        //访问网络代码
        try {
            //获取源码路径
            String path = et_path.getText().toString().trim();

            //创建一个URL 对象指定我们要访问的网址(路径)
            URL url = new URL(path);
            //拿到httpurlconnection对象,用于发射或接收数据
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置发送get请求
            conn.setRequestMethod("GET");//get要求大写,默认get请求
            //设置请求超时时间
            conn.setConnectTimeout(5000);
            //获取服务器返回的状态码
            int code = conn.getResponseCode();
            //code 为200,则请求成功
            if(code ==200){
                //从服务器返回数据,以流的形式返回,由于把流转换成字符很常见,封装成一个方法
                InputStream inputStream =conn.getInputStream();

                //使用工具类把inputstream 转换成string
                 content = StreamTools.readStream(inputStream);
                handler.post(runnableUi);

               /* //把流的数据展示到textview上
                tv_result.setText(content);*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 构建Runnable对象，在runnable中更新界面
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            tv_result.setText("the Content is:"+content);
        }

    };
}

