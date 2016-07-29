package com.peng.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ListView lv  = (ListView) findViewById(R.id.lv);
        //准备listview要现实的数据
        List<Map<String,String>> data =new ArrayList<Map<String,String >>();
        Map<String,String> map1 = new HashMap<String,String >();
        map1.put("name","张飞");
        map1.put("phone","1330");

        Map<String,String> map2 = new HashMap<String,String >();
        map2.put("name","赵云");
        map2.put("phone","110");

        Map<String,String> map3 = new HashMap<String,String >();
        map3.put("name","貂蝉");
        map3.put("phone","120");

        Map<String,String> map4 = new HashMap<String,String >();
        map4.put("name","关羽");
        map4.put("phone","1323");

        //把map加入打集合中
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);

        //设置数据适配器
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),data,
                R.layout.item,new String[]{"name","phone"},new int[]{R.id.tv_name,R.id.tv_phone});
        lv.setAdapter(adapter);
    }
}
