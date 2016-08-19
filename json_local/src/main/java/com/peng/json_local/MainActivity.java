package com.peng.json_local;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*08-19 04:20:02.875 16013-16013/com.peng.json_local W/System.err: org.json.JSONException: Value {"languages":[{"id":1,"ide":"Eclipse","name":"java"},{"id":2,"ide":"XCode","name":"Swift"},{"id":3,"ide":"Visual Studio","name":"C##"}]} of type org.json.JSONObject cannot be converted to JSONArray
           08-19 04:20:02.875 16013-16013/com.peng.json_local W/System.err:     at org.json.JSON.typeMismatch(JSON.java:111)*/
        //JSONArray array =new JSONArray(builder.toString());
        //jsonArray 读取的一定要是[]的json数据
    }

    public void click1(View view) {
        try {
            //InputStreamReader 将字节输入流转换为字符流
            InputStreamReader isr = new InputStreamReader(getAssets().open("get_data1.json"), "UTF-8");
            //包装字符流,将字符流放入缓存里
            BufferedReader br = new BufferedReader(isr);
            String line;
            //StringBuilder和StringBuffer功能类似,存储字符串
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                //append 被选元素的结尾(仍然在内部)插入指定内容,缓存的内容依次存放到builder中
                builder.append(line);
            }
            br.close();
            isr.close();
            //builder.toString()返回表示此序列中数据的字符串
            JSONObject root = new JSONObject(builder.toString());
            System.out.println("name= " + root.getString("name") +
                    " age= " + root.getInt("age") +
                    " address= " + root.getString("address"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void click2(View view) {
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("get_data2.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject lan = array.getJSONObject(i);
                System.out.println("-----------------");
                System.out.println("id= " + lan.getInt("id"));
                System.out.println("ide= " + lan.getString("ide"));
                System.out.println("name= " + lan.getString("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void click3(View view) {
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("get_data3.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
            JSONObject root = new JSONObject(builder.toString());
            System.out.println("name= " + root.getString("name") +
                    " age= " + root.getInt("age") +
                    " address= " + root.getString("address"));
            //读取多个数据
            JSONArray array = root.getJSONArray("languages");
            for (int i = 0; i < array.length(); i++) {
                JSONObject lan = array.getJSONObject(i);
                System.out.println("-----------------");
                System.out.println("id= " + lan.getInt("id"));
                System.out.println("name= " + lan.getString("name"));
                System.out.println("ide= " + lan.getString("ide"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}