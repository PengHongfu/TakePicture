package com.peng.arrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String objects[] ={"韩寒","黎明","汪涵","静静","夏雨","宝强"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ListView lv = (ListView) findViewById(R.id.lv);
        //创建一个arrayAdapter
        //ArrayAdapter<String>adapter =new ArrayAdapter<String>(this,R.layout.item,objects);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.item1,R.id.tv_name,objects);
        //设置数据适配器
        lv.setAdapter(adapter);
    }
}
