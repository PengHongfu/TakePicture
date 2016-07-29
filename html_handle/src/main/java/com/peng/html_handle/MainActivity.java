package com.peng.html_handle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    protected    final  int REQUESTSUCESS = 0;
    protected    final  int NOTFOUND = 1;
    protected    final  int REQUESTEXCEPETION = 2;
    private TextView tv_result;
    private EditText et_path;
    private String content;
    private Handler  handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //区分那个消息
            switch (msg.what){
                case REQUESTSUCESS://代表请求成功
                    String content = (String) msg.obj;
                    tv_result.setText(content);
                    break;
                case NOTFOUND://代表请求成功
                    Toast.makeText(getApplicationContext(),"请求资源不存在",Toast.LENGTH_SHORT).show();
                    break;
                case REQUESTEXCEPETION://代表异常
                    Toast.makeText(getApplicationContext(),"服务器忙,请稍候..",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et_path = (EditText) findViewById(R.id.et_path);


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

                //创建message对象
                Message msg = new Message();
                msg.what =REQUESTSUCESS;
                msg.obj =content;
                //拿到一个handle助手,告诉系统,我要更新ui
                //handler.post(runnableUi);
                handler.sendMessage(msg);
                //发了一条消息,消息里把数据放到msg里,handmessage会响应sendmessage方法

            }else{
                Message msg = new Message();
                msg.what = NOTFOUND;//代表那个消息
                handler.sendMessage(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = REQUESTEXCEPETION;//代表那个消息
            handler.sendMessage(msg);

        }
    }

   /* // 构建Runnable对象，在runnable中更新界面
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            tv_result.setText("the Content is:"+content);
        }

    };*/
}


