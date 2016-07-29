package com.peng.xml;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 private  List<Sms>  smsLists;
    private  Sms sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         smsLists =new ArrayList<Sms>();

        for(int i=0;i<10;i++) {
             sms = new Sms();
            sms.setAddress("10000"+i);
            sms.setBody("niaho"+i);
            sms.setData("2010"+i);
            smsLists.add(sms);
        }
    }
    //点击按钮 通过Stiringbuffer de 方式生成一个xml文件
    public void click(View view) {
            //创建sb对象
        StringBuffer sb  = new StringBuffer();
        //开始组拼xml文件头
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        //开始组拼xml根节点
        sb.append("<smss>");
        //开始组拼sms节点
        for(Sms sms:smsLists){
            sb.append("<sms>");

        //开始组拼address节点
         sb.append("<address>");
         sb.append(sms.getAddress());
         sb.append("</address>");
            //开始组拼Body节点
            sb.append("<body>");
            sb.append(sms.getBody());
            sb.append("</body>");

            sb.append("<data>");
            sb.append(sms.getData());
            sb.append("</data>");

            sb.append("</sms>");
        }
        sb.append("<smss>");
        //把数据保存打sd卡
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(),"smsback.xml");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
